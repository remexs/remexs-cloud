package com.remexs.auth.client.runner;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.auth.client.configuration.AuthClientConfiguration;
import com.remexs.auth.client.feign.ServerFeignService;
import com.remexs.auth.client.feign.ServerResourceFeignService;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.client.feign.ResourceFeignService;
import com.remexs.auth.client.feign.RoleFeignService;
import com.remexs.auth.client.feign.RoleResourceFeignService;
import com.remexs.auth.entity.Server;
import com.remexs.auth.entity.ServerResource;
import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.Role;
import com.remexs.auth.entity.RoleResource;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.response.Result;
import com.remexs.common.utils.ObjectUtils;
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
	@Override
	public void run(String... arg0) throws Exception {
		ServerFeignService serverFeignService = SpringUtils.getBean(ServerFeignService.class);
		ServerResourceFeignService serverResourceFeignService = SpringUtils.getBean(ServerResourceFeignService.class);
		ResourceFeignService resourceFeignService = SpringUtils.getBean(ResourceFeignService.class);
		RoleFeignService roleFeignService = SpringUtils.getBean(RoleFeignService.class);
		RoleResourceFeignService roleResourceFeignService = SpringUtils.getBean(RoleResourceFeignService.class);
		
		AuthClientConfiguration authClientConfiguration = SpringUtils.getBean(AuthClientConfiguration.class);

		Server client = new Server();
		client.setCode(authClientConfiguration.getClientCode());
		client.setName(authClientConfiguration.getClientName());
		client.setDesc(authClientConfiguration.getClientDesc());
		// 服务自动注册
		Result<String> result = serverFeignService.add(client);
		if (!result.isSuccess()) {
			logger.error("鉴权客户端服务自动注册失败：" + result.getMsg());
			throw new ServiceException("鉴权客户端服务自动注册失败：" + result.getMsg());
		}
		
		final String clientId = result.getData();

		// 刷新客户端token
		refreshAuthClientToken();
		
		refreshServerConfig();

		// 开启接口自动注册
		if ("developer".equals(authClientConfiguration.getClientMode())) {
			
			logger.info("鉴权客户端开发模式动态装在资源开始。。。。。。");
			Result<List<Server>> serverResult=serverFeignService.list(new HashDto());
			if(!serverResult.isSuccess()) {
				logger.info("查询系统中服务列表失败："+serverResult.getMsg());
			}
			List<Server> serverList=serverResult.getData();
			
			HashDto queryDto=new HashDto();
			queryDto.put("eq_parent_id", "1014894111011995650");
			Result<List<Role>> roleResult=roleFeignService.list(queryDto);
			if(!roleResult.isSuccess()) {
				logger.info("查询系统中开发组角色列表失败："+roleResult.getMsg());
			}
			List<Role> roleList=roleResult.getData();
		
			Map<String, Object> beanMaps = SpringUtils.getApplicationContext().getBeansWithAnnotation(ApiFilter.class);
			beanMaps.keySet().forEach(key -> {
				Class<?> clazz = (Class<?>) beanMaps.get(key).getClass();
				ApiFilter apiFilter = clazz.getAnnotation(ApiFilter.class);
				String apiPath = apiFilter.path();// 接口路径
				String apiCode = apiFilter.code();// 接口代码

				apiPath = apiPath.contains("/") ? apiPath : "/" + apiPath;

				// 获得接口方法集合
				Method[] methods = clazz.getMethods();

				for (Method method : methods) {

					ApiMethodFilter apiMethodFilter = method.getDeclaredAnnotation(ApiMethodFilter.class);
					if (null == apiMethodFilter) {
						continue;
					}
					//
					String apiMethodPath = apiMethodFilter.path();// 接口方法路径
					String apiMethodName = apiMethodFilter.name();// 接口方法名称
					String apiMethodCode = apiMethodFilter.code();// 接口方法代码
					String apiMethodMethod = apiMethodFilter.method();// 接口方法类型

					apiMethodCode = apiCode + ":" + apiMethodCode;

					apiMethodPath = apiPath + ("".equals(apiMethodPath) ? "" : (apiMethodPath.contains("/") ? apiMethodPath : "/" + apiMethodPath));

					Resource resource = new Resource();
					resource.setServerId(clientId);
					resource.setName(apiMethodName);
					resource.setCode(apiMethodCode);
					resource.setMethod(apiMethodMethod);
					resource.setPath(apiMethodPath);
					
					Result<String> resourceResult=resourceFeignService.add(resource);
					if(!resourceResult.isSuccess()) {
						continue;
					}
					String resourceId=resourceResult.getData();
					
					if(!ObjectUtils.isEmpty(serverList)) {
						//为当前系统下所有服务授于本资源的权限
						for(Server server:serverList) {
							ServerResource serverResource=new ServerResource();
							
							serverResource.setServerId(server.getId());
							serverResource.setResourceId(resourceId);
							serverResourceFeignService.add(serverResource);
						}
					}
					
					//为开发组所有角色分配本资源的权限
					if(!ObjectUtils.isEmpty(roleList)) {
						for(Role role:roleList) {
							RoleResource roleResource=new RoleResource();
							
							roleResource.setRoleId(role.getId());
							roleResource.setResourceId(resourceId);
							
							roleResourceFeignService.add(roleResource);
						}
					}
					
				}
			});
		}

	}

	/**
	 * 定时刷新客户端token
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void refreshAuthClientToken() {
		ServerFeignService serverFeignService = SpringUtils.getBean(ServerFeignService.class);
		AuthClientConfiguration authClientConfiguration = SpringUtils.getBean(AuthClientConfiguration.class);
		String clientCode = authClientConfiguration.getClientCode();
		String clientSecret = authClientConfiguration.getClientSecret();

		Result<String> result = serverFeignService.connect(clientCode, clientSecret);
		if (!result.isSuccess()) {
			logger.error("客户端链接鉴权中心失败，" + result.getMsg());
			return;
		}
		authClientConfiguration.setClientToken(result.getData());
	}

	/**
	 * 定时刷新取得鉴权中心配置信息
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void refreshServerConfig() {
		try {
			ServerFeignService serverFeignService = SpringUtils.getBean(ServerFeignService.class);
			AuthClientConfiguration authClientConfiguration = SpringUtils.getBean(AuthClientConfiguration.class);
			logger.debug("获得鉴权中心配置信息 .....");
			Result<HashDto> result = serverFeignService.getConfig();
			if (!result.isSuccess()) {
				logger.error("客户端获得鉴权中心配置失败," + result.getMsg());
				return;
			}
			Dto configDto = result.getData();

			authClientConfiguration.setServerCode(configDto.getString(AuthConstants.SERVER_CODE));
			
			authClientConfiguration.setClientTokenHeader(configDto.getString(AuthConstants.CLIENT_TOKEN_HEADER));
			authClientConfiguration.setClientTokenExpire(configDto.getInteger(AuthConstants.CLIENT_TOKEN_EXPIRE));
			authClientConfiguration.setClientTokenPubKey(RsaUtils.toBytes(configDto.getString(AuthConstants.CLIENT_TOKEN_PUBKEY)));
			
			authClientConfiguration.setUserTokenHeader(configDto.getString(AuthConstants.USER_TOKEN_HEADER));
			authClientConfiguration.setUserTokenExpire(configDto.getInteger(AuthConstants.USER_TOKEN_EXPIRE));
			authClientConfiguration.setUserTokenPubKey(RsaUtils.toBytes(configDto.getString(AuthConstants.USER_TOKEN_PUBKEY)));
			
		} catch (IOException e) {
			logger.error("字符串转码错误");
			e.printStackTrace();
		}

	}
}
