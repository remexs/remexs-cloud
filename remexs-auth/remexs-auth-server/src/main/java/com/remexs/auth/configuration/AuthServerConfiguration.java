package com.remexs.auth.configuration;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.Data;

/**
 * 鉴权服务 自定义配置
 * @author remexs
 *
 */
@Data
@Configuration

public class AuthServerConfiguration implements EnvironmentAware {
	private String serverName;
	private String serverMode;
	private Boolean userTokenEnable;
	private String userTokenHeader;
	private String userTokenSecret;
	private Integer userTokenExpire;

	private Boolean clientTokenEnable;
	private String clientTokenHeader;
	private String clientTokenSecret;
	private Integer clientTokenExpire;

	private byte[] userTokenPubKey;
	private byte[] userTokenPriKey;
	private byte[] clientTokenPubKey;
	private byte[] clientTokenPriKey;

	@Override
	public void setEnvironment(Environment env) {
		this.setServerMode(env.getProperty("auth.server.mode", "developer"));
		this.setServerName(env.getProperty("spring.application.name", "remexs-auth"));
		this.setUserTokenEnable(Boolean.valueOf(env.getProperty("auth.server.user-token-enable", "true")));
		this.setUserTokenHeader(env.getProperty("auth.server.user-token-header", "user-token"));
		this.setUserTokenSecret(env.getProperty("auth.server.user-token-secret", "xx1WET12^%3^(WE45"));
		this.setUserTokenExpire(Integer.valueOf(env.getProperty("auth.server.user-token-expire", "3600")));

		this.setClientTokenEnable(Boolean.valueOf(env.getProperty("auth.server.client-token-enable", "true")));
		this.setClientTokenHeader(env.getProperty("auth.server.client-token-header", "client-token"));
		this.setClientTokenSecret(env.getProperty("auth.server.client-token-secret", "x2318^^(*WRYQWR(QW&T"));
		this.setClientTokenExpire(Integer.valueOf(env.getProperty("auth.server.client-token-expire", "3600")));
	}
}
