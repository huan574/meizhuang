package com.meizhuang.quartz;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;

import org.quartz.SchedulerException;
import org.quartz.spi.InstanceIdGenerator;

public class QuartzInstanceIdGenerator implements InstanceIdGenerator {

	public String generateInstanceId() throws SchedulerException {
		try {
			MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
			Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
			String host = InetAddress.getLocalHost().getHostAddress();
			String port = objectNames.iterator().next().getKeyProperty("port");
			return host + ":" + port;
		} catch (Exception e) {
			try {
				return InetAddress.getLocalHost().getHostName() + System.currentTimeMillis();
			} catch (Exception ex) {
				throw new SchedulerException("Couldn't get host name!", e);
			}
		}
	}

}