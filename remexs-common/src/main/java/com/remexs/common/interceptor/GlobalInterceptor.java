package com.remexs.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @description 允许跨域访问配置
 * 
 * @author remexs
 * @email bigdrone@163.com
 * @date 2017年11月6日 下午5:14:52
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); // 设置允许哪些url可以跨域请求到本域，*表示所有
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, user-token, client-token");
		response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,PATCH,OPTIONS");
		return true;
	}

}
