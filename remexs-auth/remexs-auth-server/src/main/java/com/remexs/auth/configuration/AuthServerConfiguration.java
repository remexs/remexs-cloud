package com.remexs.auth.configuration;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.Data;

/**
 * 鉴权服务 自定义配置
 * 
 * @author remexs
 *
 */
@Data
@Configuration
public class AuthServerConfiguration implements EnvironmentAware {
	private String serverCode;
	
	private String clientTokenHeader;//客户端请求头
	private String clientTokenSecret;//客户端鉴权密钥
	private Integer clientTokenExpire;//客户端token过期时间
	private byte[] clientTokenPubKey;//客户端token公钥
	private byte[] clientTokenPriKey;//客户端状态token私钥
	
	private String userTokenHeader;//用户token请求头
	private String userTokenSecret;//用户token鉴权密钥
	private Integer userTokenExpire;//用户token过期时间
	private byte[] userTokenPubKey;//用户token公钥
	private byte[] userTokenPriKey;//用户token私钥

	private String authClientMode;//客户端状态
	private String authClientCode;//客户端代码
	private String authClientName;//客户端名称
	private String authClientDesc;//客户端描述
	private String authClientSecret;//客户端密钥
	private String authClientToken;//客户段token
	
	
	@Override
	public void setEnvironment(Environment env) {
		this.setServerCode(env.getProperty("spring.application.name", "remexs-auth"));
		
		this.setClientTokenHeader(env.getProperty("auth.server.client-token-header", "client-token"));
		this.setClientTokenSecret(env.getProperty("auth.server.client-token-secret", "xx1WET12^%3^(WE45"));
		this.setClientTokenExpire(Integer.valueOf(env.getProperty("auth.server.client-token-expire", "3600")));
		
		this.setUserTokenHeader(env.getProperty("auth.server.user-token-header", "user-token"));
		this.setUserTokenSecret(env.getProperty("auth.server.user-token-secret", "xx1WET12^%3^(WE45"));
		this.setUserTokenExpire(Integer.valueOf(env.getProperty("auth.server.user-token-expire", "3600")));
		
		this.setAuthClientMode(env.getProperty("auth.client.mode"));
		this.setAuthClientCode(env.getProperty("spring.application.name"));
		this.setAuthClientName(env.getProperty("auth.client.name"));
		this.setAuthClientDesc(env.getProperty("auth.client.desc"));
		this.setAuthClientSecret(env.getProperty("auth.client.secret"));
	}
}
