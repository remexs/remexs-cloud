package com.remexs.auth.client.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.context.UserContext;
import com.remexs.common.dto.Dto;
import com.remexs.common.exception.ErrCode;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.JwtTokenUtils;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.SpringUtils;

import io.jsonwebtoken.JwtException;

/**
 * 用户 API 访问鉴权过滤器
 * 
 * @author remexs
 *
 */
public class AuthClientUserRestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthClientConfiguration authClientConfiguration = (AuthClientConfiguration) SpringUtils.getBean("authClientConfiguration");
		// 判断鉴权中心是否开客户端用户鉴权
		if (!authClientConfiguration.getAuthServerUserTokenEnable()) {
			return super.preHandle(request, response, handler);
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		ApiMethodFilter apiMethodFilter = handlerMethod.getMethodAnnotation(ApiMethodFilter.class);
		// 判断是否启用用户token验证
		if (!ObjectUtils.isEmpty(apiMethodFilter)) {
			if (!apiMethodFilter.userToken()) {
				return super.preHandle(request, response, handler);
			}
		}
		
		String token = request.getHeader(authClientConfiguration.getAuthServerUserTokenHeader());

		if (null == token)
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不能为空");
		// jwt token 鉴权
		Dto jtwInfo;
		try {
			jtwInfo = JwtTokenUtils.getInfoFromToken(token, authClientConfiguration.getAuthServerClientTokenPubKey());
		} catch (JwtException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不合法");
		} catch (IllegalArgumentException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不能为空");
		}

		UserContext.setUserID(jtwInfo.getString("userId"));
		UserContext.setUsername(jtwInfo.getString("userName"));
		UserContext.setUserAccount(jtwInfo.getString("subject"));
		UserContext.setToken(token);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		UserContext.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
