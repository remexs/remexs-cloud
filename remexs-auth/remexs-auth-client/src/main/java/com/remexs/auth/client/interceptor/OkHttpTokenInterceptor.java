package com.remexs.auth.client.interceptor;

import java.io.IOException;

import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.common.context.UserContext;
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

		if (!ObjectUtils.isEmpty(authClientConfiguration.getAuthServerUserTokenHeader())) {
			System.out.println(UserContext.getToken());
			request = request
					.newBuilder()
					.addHeader(authClientConfiguration.getAuthServerUserTokenHeader(), UserContext.getToken())
					.build();
		}
		System.out.println(request);

		return chain.proceed(request);
	}

}
