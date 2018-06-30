package com.remexs.auth.runner;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.common.utils.RedisUtils;
import com.remexs.common.utils.RsaUtils;
import com.remexs.common.utils.SpringUtils;

/**
 * 自动注入jwt鉴权密钥
 * @author remexs
 *
 */
@Configuration
public class AuthServerJwtRunner implements CommandLineRunner {
	Logger logger =LoggerFactory.getLogger(AuthServerJwtRunner.class);
	/**
	 * 用户token验证公钥
	 */
	private static final String REDIS_AUTH_USER_PUB_KEY = "REMEX:AUTH:USER:PUB";
	/**
	 * 用户token验证私钥
	 */
	private static final String REDIS_AUTH_USER_PRI_KEY = "REMEX:AUTH:USER:PRI";
	/**
	 * 客户端token验证公钥
	 */
	private static final String REDIS_AUTH_CLIENT_PUB_KEY = "REMEX:AUTH:CLIENT:PUB";
	/**
	 * 客户端token验证私钥
	 */
	private static final String  REDIS_AUTH_CLIENT_PRI_KEY= "REMEX:AUTH:CLIENT:PRI";

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("-----------------注入redistemplate到RedisUtils-------------------------");
		RedisUtils.setRedisTemplate((RedisTemplate) SpringUtils.getBean("stringRedisTemplate"));
		logger.info("-----------------rsa生产密钥-------------------------");
		AuthServerConfiguration authServerConfiguration =(AuthServerConfiguration) SpringUtils.getBean("authServerConfiguration");
		if (
				RedisUtils.hasKey(REDIS_AUTH_USER_PUB_KEY) 
				&& RedisUtils.hasKey(REDIS_AUTH_USER_PRI_KEY)
				&& RedisUtils.hasKey(REDIS_AUTH_CLIENT_PUB_KEY)
				&& RedisUtils.hasKey(REDIS_AUTH_CLIENT_PRI_KEY)
		) {
			logger.info("-----------------缓存中获得公钥和私钥（包含用户验证和，客户端验证）-------------------------");
			//缓存中获得公钥和私钥（包含用户验证和，客户端验证）
			authServerConfiguration.setUserTokenPriKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_USER_PRI_KEY).toString()));
			authServerConfiguration.setUserTokenPubKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_USER_PUB_KEY).toString()));
			authServerConfiguration.setClientTokenPriKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_CLIENT_PRI_KEY).toString()));
			authServerConfiguration.setClientTokenPubKey(RsaUtils.toBytes(RedisUtils.get(REDIS_AUTH_CLIENT_PUB_KEY).toString()));
			
		}else {
			logger.info("-----------------生成公钥和私钥（包含用户验证和，客户端验证）-------------------------");
			//生成公钥和私钥（包含用户验证和，客户端验证）
			Map<String, byte[]> userTokenKeyMap = RsaUtils.generateKey(authServerConfiguration.getUserTokenSecret());
			
			authServerConfiguration.setUserTokenPriKey(userTokenKeyMap.get("pri"));
			authServerConfiguration.setUserTokenPubKey(userTokenKeyMap.get("pub"));
			
			RedisUtils.set(REDIS_AUTH_USER_PRI_KEY, RsaUtils.toHexString(userTokenKeyMap.get("pri")));
			RedisUtils.set(REDIS_AUTH_USER_PUB_KEY, RsaUtils.toHexString(userTokenKeyMap.get("pub")));
			
			Map<String, byte[]> clientTokenKeyMap = RsaUtils.generateKey(authServerConfiguration.getClientTokenSecret());
			
			authServerConfiguration.setClientTokenPriKey(clientTokenKeyMap.get("pri"));
			authServerConfiguration.setClientTokenPubKey(clientTokenKeyMap.get("pub"));
			
			RedisUtils.set(REDIS_AUTH_CLIENT_PRI_KEY, RsaUtils.toHexString(clientTokenKeyMap.get("pri")));
			RedisUtils.set(REDIS_AUTH_CLIENT_PUB_KEY, RsaUtils.toHexString(clientTokenKeyMap.get("pub")));

		}
	}

}
