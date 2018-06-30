package com.remexs.auth.client.configuration;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


import lombok.Data;
/**
 * 鉴权客户端配置
 * @author remexs
 *
 */
@Data
@Configuration
public class AuthClientConfiguration implements EnvironmentAware{
	
	private String authClientServerId;
	private String authClientTokenHeader;
	@Override
	public void setEnvironment(Environment environment) {
		this.setAuthClientServerId(environment.getProperty("auth.client.server-id", "remexs-auth"));
		this.setAuthClientTokenHeader(environment.getProperty("auth.client.client-token-header", "client-token"));
	}
	
}
