package com.remexs.auth.client.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 鉴权客户端配置
 * 
 * @author remexs
 *
 */
@Data
@Configuration
public class AuthClientConfiguration {
	/**
	 * 鉴权中心应用名
	 */
	private String authServerName;
	/**
	 * 鉴权中心应用模式
	 */
	private String authServerMode;
	/**
	 * 鉴权中心是否开启用户鉴权
	 */
	private Boolean authServerUserTokenEnable;
	/**
	 * 鉴权中心用户鉴权token名
	 */
	private String authServerUserTokenHeader;
	/**
	 * 鉴权中心用户鉴权公钥信息（用于解密用户token）
	 */
	private byte[] authServerUserTokenPubKey;
	
	/**
	 * 鉴权中心是否开启用户鉴权
	 */
	private Boolean authServerClientTokenEnable;
	/**
	 * 鉴权中心服务鉴权token名
	 */
	private String authServerClientTokenHeader;
	/**
	 * 鉴权中心服务鉴权公钥信息（用于解密服务token）
	 */
	private byte[] authServerClientTokenPubKey;


}
