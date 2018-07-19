package com.remexs.auth.client.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.auth.client.feign.UserFeignService;
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
 * 用户权限过滤
 * 
 * @author remexs
 *
 */
public class AuthUserTokenInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(AuthUserTokenInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getMethod().equals("OPTIONS")) {
			return super.preHandle(request, response, handler);
		}
		AuthClientConfiguration authServerConfiguration = SpringUtils.getBean(AuthClientConfiguration.class);
		logger.debug("鉴权中中心用户访问过滤");
		logger.debug(authServerConfiguration.toString());

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 获得请求方法的代码
		ApiFilter apiFilter = handlerMethod.getBeanType().getAnnotation(ApiFilter.class);
		String apiCode = apiFilter.code();

		ApiMethodFilter apiMethodFilter = handlerMethod.getMethodAnnotation(ApiMethodFilter.class);
		String apiMethodCode = apiCode + ":" + apiMethodFilter.code();
		
		String methodName = handlerMethod.getMethod().getName();
		String controllerName = handlerMethod.getBeanType().getName();

		
		String logStr = "当前用户访问的controller" + controllerName + ",用户访问的方法名：" + methodName;

		// 判断是否启用token验证
		if (ObjectUtils.isEmpty(apiMethodFilter)) {
			return super.preHandle(request, response, handler);
		}
		if (!apiMethodFilter.userTokenFilter()) {
			logger.info(logStr + ",本方法不参与用户验证");
			return super.preHandle(request, response, handler);
		}

		
		Dto tokenInfo;
		String token = request.getHeader(authServerConfiguration.getUserTokenHeader());

		// 解析token
		if (null == token)
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "用户没有权限访问该方法");
		try {
			tokenInfo = JwtTokenUtils.getInfoFromToken(token, authServerConfiguration.getClientTokenPubKey());
		} catch (JwtException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "用户token不合法");
		} catch (IllegalArgumentException ex) {
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "用户token不能为空");
		} finally {
			logger.info(logStr + ",解析客户端token错误！");
		}

		String userId = tokenInfo.getString(AuthConstants.CURRENT_USER_ID);
		String userName=tokenInfo.getString(AuthConstants.CURRENT_USER_NAME);
		String userAccount = tokenInfo.getString(AuthConstants.CURRENT_USER_ACCOUNT);
		
		// 查询指定请求来源客户端是否分配了当前方法的访问权限
		UserFeignService userFeignService = SpringUtils.getBean(UserFeignService.class);
		Result<Boolean> result=userFeignService.hasPromiseBy(userId, apiMethodCode);
		if (!result.isSuccess()&&!result.getData()) {
			logger.info(userName + ":" + logStr + "用户没有调用该方法权限");
			throw new ServiceException(ErrCode.UNAUTHORIZED.getCode(), "用户:" + userName + "没有调用该方法权限");
		}

		// 鉴权上下文保存客户端信息
		AuthContext.set(AuthConstants.CURRENT_USER_ID, userId);
		AuthContext.set(AuthConstants.CURRENT_USER_NAME, userName);
		AuthContext.set(AuthConstants.CURRENT_USER_ACCOUNT, userAccount);
		AuthContext.set(AuthConstants.CURRENT_CLIENT_TOKEN, token);

		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		AuthContext.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
