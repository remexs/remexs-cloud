package com.remexs.auth.client.runner;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.auth.client.feign.AuthServerService;
import com.remexs.auth.client.feign.ResourceService;
import com.remexs.auth.client.feign.RoleService;
import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.Role;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.utils.RsaUtils;
import com.remexs.common.utils.SpringUtils;

/**
 * 自动系统资源
 * 
 * @author remexs
 *
 */
@Configuration
@EnableScheduling

public class AuthClientRunner implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(AuthClientRunner.class);

	@Autowired
	private AuthServerService authServerService;
	@Autowired
	@Lazy
	AuthClientConfiguration authClientConfiguration;

	@Override
	public void run(String... arg0) throws Exception {
		// 获得鉴权中心配置信息
		AuthServerService authServerService = SpringUtils.getBean(AuthServerService.class);
		// 获得鉴权中心配置信息
		Result<HashDto> result = authServerService.getServerConfig();
		logger.info(result.getMsg());
		logger.info(result.getData().toJson());
		logger.info(""+authClientConfiguration);
		if (result.isSuccess()) {
			Dto configDto = result.getData();
			setAuthClientConfig(configDto);
			logger.info(""+authClientConfiguration);
			if ("developer".equals(configDto.getString(configDto.getString("serverMode")))) {
				RoleService roleService = SpringUtils.getBean(RoleService.class);
				ResourceService resourceService = SpringUtils.getBean(ResourceService.class);
				logger.info("鉴权客户端开发模式动态装在资源开始。。。。。。");
				Map<String, Object> beanMaps = SpringUtils.getApplicationContext().getBeansWithAnnotation(ApiFilter.class);
				beanMaps.keySet().forEach(key -> {
					Class<?> clazz = (Class<?>) beanMaps.get(key).getClass();
					ApiFilter apiFilter = clazz.getAnnotation(ApiFilter.class);
					String apiPath = apiFilter.path();// 接口路径
					String apiName = apiFilter.name();// 接口名称
					String apiCode = apiFilter.code();// 接口代码

					apiPath = apiPath.contains("/") ? apiPath : "/" + apiPath;

					// 获得接口方法集合
					Method[] methods = clazz.getMethods();

					for (Method method : methods) {
						Resource resource = new Resource();

						ApiMethodFilter apiMethodFilter = method.getDeclaredAnnotation(ApiMethodFilter.class);
						if (null == apiMethodFilter) {
							continue;
						}
						String apiMethodPath = apiMethodFilter.path();// 接口方法路径
						String apiMethodName = apiMethodFilter.name();// 接口方法名称
						String apiMethodCode = apiMethodFilter.code();// 接口方法代码
						String apiMethodMethod = apiMethodFilter.method();// 接口方法类型

						String[] apiMethodRoles = apiMethodFilter.roles();// 接口角色列表

						// 角色信息
						for (String roleName : apiMethodRoles) {
							Role role = new Role();
							role.setName(roleName);

							roleService.add(role);
						}

						apiMethodCode = apiCode + ":" + apiMethodCode;

						apiMethodPath = apiPath + ("".equals(apiMethodPath) ? "" : (apiMethodPath.contains("/") ? apiMethodPath : "/" + apiMethodPath));

						resource.setName(apiMethodName);
						resource.setCode(apiMethodCode);
						resource.setMethod(apiMethodMethod);
						resource.setType("uri");
						resource.setPath(apiMethodPath);

						resourceService.add(resource);
					}
				});
			}
		}

		
	}

	/**
	 * 
	 * @param configDto
	 */
	private void setAuthClientConfig(Dto configDto) {
		try {
			logger.debug("配置客户端 鉴权参数 .....");
			if (null != authClientConfiguration) {
				logger.debug("配置客户端 鉴权参数 .....");
				authClientConfiguration.setAuthServerMode(configDto.getString("serverMode"));
				authClientConfiguration.setAuthServerName(configDto.getString("serverName"));
				authClientConfiguration.setAuthServerUserTokenEnable(configDto.getBoolean("serverUserTokenEnable"));
				authClientConfiguration.setAuthServerUserTokenHeader(configDto.getString("serverUserTokenHeader"));
				authClientConfiguration.setAuthServerUserTokenPubKey(RsaUtils.toBytes(configDto.getString("serverUserTokenPubKey")));
				authClientConfiguration.setAuthServerClientTokenEnable(configDto.getBoolean("serverClientTokenEnable"));
				authClientConfiguration.setAuthServerClientTokenHeader(configDto.getString("serverClientTokenHeader"));
				authClientConfiguration.setAuthServerClientTokenPubKey(RsaUtils.toBytes(configDto.getString("serverClientTokenPubKey")));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 定时刷新取得鉴权中心配置信息
	 */
	
    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshServerConfig() {
    	logger.debug("获得鉴权中心配置信息 .....");
    	Result<HashDto> result = authServerService.getServerConfig();
		if (result.isSuccess()) {
			Dto configDto = result.getData();
			setAuthClientConfig(configDto);
		}
    	
    }

}
