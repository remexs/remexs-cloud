package com.remexs.auth.runner;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.common.utils.RedisUtils;
import com.remexs.common.utils.RsaUtils;
import com.remexs.common.utils.SpringUtils;

/**
 * 自动注入jwt鉴权密钥
 * 
 * @author remexs
 *
 */
@Configuration
@Order(1)
public class AuthServerJwtRunner implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(AuthServerJwtRunner.class);
	/**
	 * 服务token验证公钥
	 */
	private static final String REDIS_AUTH_CLIENT_TOKEN_PUB_KEY = "REMEX:AUTH:CLIENT:TOKEN:PUB";
	/**
	 * 服务token验证私钥
	 */
	private static final String REDIS_AUTH_CLIENT_TOKEN_PRI_KEY = "REMEX:AUTH:CLIENT:TOKEN:PRI";

	/**
	 * 服务token验证公钥
	 */
	private static final String REDIS_AUTH_USER_TOKEN_PUB_KEY = "REMEX:AUTH:USER:TOKEN:PUB";
	/**
	 * 服务token验证私钥
	 */
	private static final String REDIS_AUTH_USER_TOKEN_PRI_KEY = "REMEX:AUTH:USER:TOKEN:PRI";

	
	/**
	 * 服务端token验证公钥
	 */

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("-----------------注入redistemplate到RedisUtils-------------------------");
		RedisUtils.setRedisTemplate((RedisTemplate) SpringUtils.getBean("stringRedisTemplate"));
		logger.info("-----------------rsa生产密钥-------------------------");
		AuthServerConfiguration authServerConfiguration = (AuthServerConfiguration) SpringUtils.getBean("authServerConfiguration");
		if (
				RedisUtils.hasKey(REDIS_AUTH_CLIENT_TOKEN_PUB_KEY) 
				&& RedisUtils.hasKey(REDIS_AUTH_CLIENT_TOKEN_PRI_KEY)
				&& RedisUtils.hasKey(REDIS_AUTH_USER_TOKEN_PUB_KEY)
				&& RedisUtils.hasKey(REDIS_AUTH_USER_TOKEN_PRI_KEY)
		) {
			logger.info("-----------------缓存中获得公钥和私钥（包含用户验证和，客户端验证）-------------------------");
			// 缓存中获得公钥和私钥（客户端验证）
			authServerConfiguration.setClientTokenPriKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_CLIENT_TOKEN_PRI_KEY).toString()));
			authServerConfiguration.setClientTokenPubKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_CLIENT_TOKEN_PUB_KEY).toString()));
			// 缓存中获得公钥和私钥（用户验证 ）
			authServerConfiguration.setUserTokenPriKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_USER_TOKEN_PRI_KEY).toString()));
			authServerConfiguration.setUserTokenPubKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_USER_TOKEN_PUB_KEY).toString()));

		} else {
			logger.info("-----------------生成公钥和私钥（包含用户验证和，客户端验证）-------------------------");
			// 生成公钥和私钥（客户端验证）
			Map<String, byte[]> clientTokenKeyMap = RsaUtils.generateKey(authServerConfiguration.getClientTokenSecret());

			authServerConfiguration.setClientTokenPriKey(clientTokenKeyMap.get("pri"));
			authServerConfiguration.setClientTokenPubKey(clientTokenKeyMap.get("pub"));

			RedisUtils.set(REDIS_AUTH_CLIENT_TOKEN_PRI_KEY, RsaUtils.toHexString(clientTokenKeyMap.get("pri")));
			RedisUtils.set(REDIS_AUTH_CLIENT_TOKEN_PUB_KEY, RsaUtils.toHexString(clientTokenKeyMap.get("pub")));

			// 生成公钥和私钥（用户验证）
			Map<String, byte[]> userTokenKeyMap = RsaUtils.generateKey(authServerConfiguration.getUserTokenSecret());

			authServerConfiguration.setClientTokenPriKey(userTokenKeyMap.get("pri"));
			authServerConfiguration.setClientTokenPubKey(userTokenKeyMap.get("pub"));

			RedisUtils.set(REDIS_AUTH_USER_TOKEN_PRI_KEY, RsaUtils.toHexString(userTokenKeyMap.get("pri")));
			RedisUtils.set(REDIS_AUTH_USER_TOKEN_PUB_KEY, RsaUtils.toHexString(userTokenKeyMap.get("pub")));

		}
	}

}
