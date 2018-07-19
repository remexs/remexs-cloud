package com.remexs.gate.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.common.dto.Dto;
import com.remexs.common.response.ResultUtils;
import com.remexs.common.utils.JwtTokenUtils;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.SpringUtils;

/**
 * 鉴权过滤 系统访问前过来
 * 
 * @author remexs
 *
 */
@Component
public class AuthFilter extends ZuulFilter {
	private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	@Value("${zuul.prefix}")
	private String zuulPrefix;

	@Value("${gate.ignore.path}")
	private String ignorePath;

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
		final String method = request.getMethod();
		final String serviceId = (String) RequestContext.getCurrentContext().get("proxy");
		logger.info(String.format("%s >>> %s >>> %s", serviceId, method, requestUri));
		if (isIgnorePath(requestUri)) {
			return null;
		}

		AuthClientConfiguration authClientConfiguration = SpringUtils.getBean(AuthClientConfiguration.class);
		String userTokenHeader = authClientConfiguration.getUserTokenHeader();

		// 从请求头中取得用户token
		String userToken = request.getHeader(userTokenHeader);
		// 从参数中取得用户token
		if (ObjectUtils.isEmpty(userToken)) {
			userToken = request.getParameter("token");
		}
		if (ObjectUtils.isEmpty(userToken)) {
			String response = ResultUtils.error("用户token信息不合法！").toJson();
			logger.debug("Reporting error ({}): {}", 200, response);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(response);
			ctx.setSendZuulResponse(false);
			return null;
		}
		// 解析获得用户token中的信息
		try {
			Dto userDto = JwtTokenUtils.getInfoFromToken(userToken, authClientConfiguration.getUserTokenPubKey());

			String userId = userDto.getString(AuthConstants.CURRENT_USER_ID);
			String userName = userDto.getString(AuthConstants.CURRENT_USER_NAME);
			String userAcount = userDto.getString(AuthConstants.CURRENT_USER_ACCOUNT);

			// 此处不做鉴权处理，中心鉴权所有客户端添加拦截器 authClientInterceptor 和 authUserInterceptor 实现鉴权

			// 转发用户token
			ctx.addZuulRequestHeader(userToken, userToken);
			// 添加应用验证
			ctx.addZuulRequestHeader(authClientConfiguration.getClientTokenHeader(), authClientConfiguration.getClientToken());

		} catch (Exception e) {
			String response = ResultUtils.error("用户token信息不合法！").toJson();
			logger.debug("Reporting error ({}): {}", 200, response);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(response);
			ctx.setSendZuulResponse(false);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (request.getMethod().equals("OPTIONS")) {
			return false;
		}
		return true;
	}

	@Override
	public int filterOrder() {

		return 1;
	}

	@Override
	public String filterType() {

		return "pre";
	}

	/**
	 * URI是否以什么打头
	 *
	 * @param uri
	 * @return
	 */
	private boolean isIgnorePath(String uri) {
		boolean flag = false;
		// 不进行拦截的地址
		if ("*".equals(ignorePath)) {
			return true;
		}
		for (String path : ignorePath.split(",")) {
			if (uri.startsWith(path)) {
				return true;
			}
		}
		return flag;
	}
}
