package com.remexs.auth.client.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.auth.client.feign.ServerFeignService;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.context.AuthContext;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.Dto;
import com.remexs.common.exception.ErrCode;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.response.Result;
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
public class AuthClientTokenRestInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(AuthClientTokenRestInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthClientConfiguration authClientConfiguration = SpringUtils.getBean(AuthClientConfiguration.class);
		logger.debug("鉴权中心访问拦截");
		logger.debug(authClientConfiguration.toString());
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		ApiMethodFilter apiMethodFilter = handlerMethod.getMethodAnnotation(ApiMethodFilter.class);
		// 判断是否启用token验证
		if (!ObjectUtils.isEmpty(apiMethodFilter)) {
			if (!apiMethodFilter.clientTokenFilter()) {
				logger.info("非客户端过了，方法名：" + handlerMethod.getMethod().getName());
				return super.preHandle(request, response, handler);
			}
		}
		
		//获得请求方法的代码
		ApiFilter apiFilter =handlerMethod.getBeanType().getAnnotation(ApiFilter.class);
		String apiCode=apiFilter.code();
		String apiMethodCode = apiCode + ":" + apiMethodFilter.code();
		
		Dto tokenInfo;
		String token = request.getHeader(authClientConfiguration.getClientTokenHeader());

		//解析token
		if (null == token)
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不能为空");
		try {
			tokenInfo = JwtTokenUtils.getInfoFromToken(token, authClientConfiguration.getClientTokenPubKey());
		} catch (JwtException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不合法");
		} catch (IllegalArgumentException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "token不能为空");
		}
		
		String clientCode=tokenInfo.getString(AuthConstants.CURRENT_CLIENT_CODE);
		
		logger.info("来自"+clientCode+"的客户请求端方法名：" + handlerMethod.getMethod().getName());
		
		//查询指定请求来源客户端是否分配了当前方法的访问权限
		ServerFeignService serverResourceFeignService  =SpringUtils.getBean(ServerFeignService.class);
		Result<Boolean> result=serverResourceFeignService.hasPromiseBy(clientCode, apiMethodCode);
		
		if(!result.isSuccess()&&!result.getData()) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "客户端："+clientCode+"没有调用该方法权限");
		}
		
		//鉴权上下文保存客户端信息
		AuthContext.set(AuthConstants.CURRENT_CLIENT_ID, tokenInfo.get(AuthConstants.CURRENT_CLIENT_ID));
		AuthContext.set(AuthConstants.CURRENT_CLIENT_CODE, tokenInfo.get(AuthConstants.CURRENT_CLIENT_CODE));
		AuthContext.set(AuthConstants.CURRENT_CLIENT_TOKEN, token);
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		AuthContext.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
