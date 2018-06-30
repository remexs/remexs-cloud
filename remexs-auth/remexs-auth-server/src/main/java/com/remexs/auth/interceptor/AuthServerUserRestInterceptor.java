package com.remexs.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.remexs.auth.annotation.IgnoreAuthUserToken;
import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.common.context.UserContext;
import com.remexs.common.dto.Dto;
import com.remexs.common.exception.ErrCode;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.JwtTokenUtils;
import com.remexs.common.utils.SpringUtils;

import io.jsonwebtoken.JwtException;

/**
 * 用户 API 访问鉴权过滤器
 * 
 * @author remexs
 *
 */
public class AuthServerUserRestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AuthServerConfiguration authServerConfiguration = (AuthServerConfiguration) SpringUtils.getBean("authServerConfiguration");
		// 判断是否开启用户鉴权服务
		if (!authServerConfiguration.getClientTokenEnable()) {
			return super.preHandle(request, response, handler);
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 配置该注解，说明不进行用户拦截
		IgnoreAuthUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreAuthUserToken.class);
		if (annotation == null) {
			annotation = handlerMethod.getMethodAnnotation(IgnoreAuthUserToken.class);
		}
		if (annotation != null) {
			return super.preHandle(request, response, handler);
		}
		
		String token = request.getHeader(authServerConfiguration.getUserTokenHeader());
		if (null == token)
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不能为空");
		// jwt token 鉴权
		Dto jtwInfo;
		try {
			jtwInfo = JwtTokenUtils.getInfoFromToken(token, authServerConfiguration.getUserTokenPubKey());
		} catch (JwtException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不合法");
		} catch (IllegalArgumentException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不能为空");
		}

		UserContext.setUserID(jtwInfo.getString("userId"));
		UserContext.setUsername(jtwInfo.getString("userName"));
		UserContext.setUserAccount(jtwInfo.getString("subject"));

		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		UserContext.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
