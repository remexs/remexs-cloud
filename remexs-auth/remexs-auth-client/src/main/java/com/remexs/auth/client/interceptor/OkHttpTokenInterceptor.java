package com.remexs.auth.client.interceptor;

import java.io.IOException;

import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.context.AuthContext;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.SpringUtils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

@Component
public class OkHttpTokenInterceptor implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {
		AuthClientConfiguration authClientConfiguration = (AuthClientConfiguration) SpringUtils.getBean(AuthClientConfiguration.class);
		Request request = chain.request();
		
		String clientTokenHeader= authClientConfiguration.getClientTokenHeader();
		String clientToken= authClientConfiguration.getClientToken();
		
		String userTokenHeader= authClientConfiguration.getUserTokenHeader();
		Object userToken= AuthContext.get(AuthConstants.CURRENT_USER_TOKEN);
		
		Request.Builder builder= request.newBuilder();
		
		if (!ObjectUtils.isEmpty(clientToken) && !ObjectUtils.isEmpty(clientTokenHeader)) {
			builder.addHeader(clientTokenHeader, clientToken.toString());
		}
		
		if(!ObjectUtils.isEmpty(userTokenHeader)&&!ObjectUtils.isEmpty(userToken)) {
			builder.addHeader(userTokenHeader, userToken.toString());
		}
		
		return chain.proceed(builder.build());
	}

}
