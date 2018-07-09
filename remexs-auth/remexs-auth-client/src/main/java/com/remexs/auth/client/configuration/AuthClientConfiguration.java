package com.remexs.auth.client.configuration;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.Data;

/**
 * 鉴权客户端配置
 * 
 * @author remexs
 *
 */
@Data
@Configuration
public class AuthClientConfiguration implements EnvironmentAware {

	private String serverCode;
	/**
	 * 鉴权中心客户端鉴权token名
	 */
	private String clientTokenHeader;
	/**
	 * 鉴权中心客户端鉴权公钥信息（用于解密客户端token）
	 */
	private byte[] clientTokenPubKey;
	
	private Integer clientTokenExpire;//客户端token过期时间
	
	/**
	 * 鉴权中心客户端鉴权token名
	 */
	private String userTokenHeader;
	/**
	 * 鉴权中心客户端鉴权公钥信息（用于解密客户端token）
	 */
	private byte[] userTokenPubKey;
	
	private Integer userTokenExpire;//客户端token过期时间
	
	
	/**
	 * 客户端状态
	 */
	private String clientMode;
	/**
	 * 客户端代码
	 */
	private String clientCode;
	/**
	 * 客户端名称
	 */
	private String clientName;
	/**
	 * 客户端描述
	 */
	private String clientDesc;
	/**
	 * 客户端密钥
	 */
	private String clientSecret;
	/**
	 * 客户段token
	 */
	private String clientToken;

	@Override
	public void setEnvironment(Environment env) {
		this.setClientMode(env.getProperty("auth.client.mode"));
		this.setClientCode(env.getProperty("spring.application.name"));
		this.setClientName(env.getProperty("auth.client.name"));
		this.setClientDesc(env.getProperty("auth.client.desc"));
		this.setClientSecret(env.getProperty("auth.client.secret"));
	}
}
