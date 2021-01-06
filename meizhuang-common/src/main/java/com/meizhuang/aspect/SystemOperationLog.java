package com.meizhuang.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.meizhuang.entity.enums.SystemOperationType;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemOperationLog {

	String value() default "";

	/**
	 * 操作类型(enum):主要是select,insert,update,delete
	 */
	SystemOperationType operationType() default SystemOperationType.UNKNOWN;

}
