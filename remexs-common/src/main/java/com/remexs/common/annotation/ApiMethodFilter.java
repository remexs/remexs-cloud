package com.remexs.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
/**
 * 接口方法访问过滤注解
 * 
 * @author remexs
 *
 */
public @interface ApiMethodFilter {
	/**
	 * 接口方法名称
	 * 
	 * @return
	 */
	String name() default "";
	/**
	 * 接口方法名称代码
	 * 
	 * @return
	 */
	String code() default "";
	/**
	 * 接口方法请求类型
	 * 
	 * @return
	 */
	String method() default "GET";
	/**
	 *  资源路径
	 * @return
	 */
	String path() default "";

	/**
	 * 是否启客户端验证
	 * 
	 * @return
	 */
	boolean clientTokenFilter() default false;
	
	/**
	 * 是否用户验证
	 * @return
	 */
	boolean userTokenFilter() default false;
	/**
	 * 接口访问角色列表
	 * 
	 * @return
	 */
	String[] roles() default {};
}
