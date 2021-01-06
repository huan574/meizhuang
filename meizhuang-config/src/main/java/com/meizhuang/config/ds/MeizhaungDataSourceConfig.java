package com.meizhuang.config.ds;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MeizhaungDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "meizhuangSqlSessionFactory")
public class MeizhaungDataSourceConfig {

	// 精确到 meizhuang 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.meizhuang.mapper.meizhuang";
	// 数据源配置文件前缀
	static final String DATASOURCE_PREFIX = "spring.datasource.meizhuang";

	@ConfigurationProperties(prefix = MeizhaungDataSourceConfig.DATASOURCE_PREFIX)
	@Bean(name = "meizhuangDataSource")
	@Primary
	public DataSource meizhuangDataSource() {
		return new DruidDataSource();
	}

	@Bean(name = "meizhuangSqlSessionFactory")
	@Primary
	public SqlSessionFactory meizhuangSqlSessionFactory(@Qualifier("meizhuangDataSource") DataSource meizhuangDataSource) throws Exception {
		final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(meizhuangDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));

		// 配置分页插件和乐观锁插件
		Interceptor[] interceptor = { new PaginationInterceptor(), new OptimisticLockerInterceptor() };
		sessionFactory.setPlugins(interceptor);

		// 打印sql配置
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setMapUnderscoreToCamelCase(true); // 开启下划线转驼峰
		sessionFactory.setConfiguration(configuration);

		return sessionFactory.getObject();
	}

	@Bean(name = "meizhuangTransactionManager")
	@Primary
	public DataSourceTransactionManager meizhuangTransactionManager(@Qualifier("meizhuangDataSource") DataSource meizhuangDataSource) {
		return new DataSourceTransactionManager(meizhuangDataSource());
	}

}