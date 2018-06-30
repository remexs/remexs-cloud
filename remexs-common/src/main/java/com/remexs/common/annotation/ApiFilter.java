package com.remexs.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口访问过滤注解
 * 
 * @author remexs
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiFilter {
	/**
	 * 接口名称
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * 接口代码
	 * 
	 * @return
	 */
	String code() default "";

	/**
	 *  资源路径
	 * @return
	 */
	String path() default "";



}
