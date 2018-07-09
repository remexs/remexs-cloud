package com.remexs.auth.runner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.auth.entity.Server;
import com.remexs.auth.entity.ServerResource;
import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.Role;
import com.remexs.auth.entity.RoleResource;
import com.remexs.auth.service.ResourceService;
import com.remexs.auth.service.RoleResourceService;
import com.remexs.auth.service.RoleService;
import com.remexs.auth.service.ServerResourceService;
import com.remexs.auth.service.ServerService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.SpringUtils;

/**
 * 自动系统资源
 * 
 * @author remexs
 *
 */
@Configuration
@Order(2)
public class AuthServerRunner implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(AuthServerRunner.class);

	@Override
	public void run(String... arg0) throws Exception {
		ServerService serverService = SpringUtils.getBean(ServerService.class);
		ResourceService resourceService = SpringUtils.getBean(ResourceService.class);
		ServerResourceService serverResourceService = SpringUtils.getBean(ServerResourceService.class);
		RoleService roleService = SpringUtils.getBean(RoleService.class);
		RoleResourceService roleResourceService = SpringUtils.getBean(RoleResourceService.class);
		
		
		AuthServerConfiguration authClientConfiguration = SpringUtils.getBean(AuthServerConfiguration.class);

		Server client = new Server();
		client.setCode(authClientConfiguration.getAuthClientCode());
		client.setName(authClientConfiguration.getAuthClientName());
		client.setDesc(authClientConfiguration.getAuthClientDesc());
		// 服务自动注册
		serverService.insertOrUpdate(client);
		
		final String clientId = client.getId();


		// 开启接口自动注册
		if ("developer".equals(authClientConfiguration.getAuthClientMode())) {
			
			logger.info("鉴权客户端开发模式动态装在资源开始。。。。。。");
			Map<String, Object> beanMaps = SpringUtils.getApplicationContext().getBeansWithAnnotation(ApiFilter.class);
			List<Server> serverList=serverService.selectList(null);
			
			HashDto queryDto=new HashDto();
			queryDto.put("eq_parent_id", "1014894111011995650");
			List<Role> roleList=roleService.list(queryDto,null);
			
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

					resourceService.insertOrUpdate(resource);
					resource=resourceService.getBy(apiMethodCode);
					String resourceId=resource.getId();
					//为当前系统下所有服务授于本资源的权限
					for(Server server:serverList) {
						ServerResource serverResource=new ServerResource();
						
						serverResource.setServerId(server.getId());
						serverResource.setResourceId(resourceId);
						serverResourceService.insertOrUpdate(serverResource);
					}
					
					//为开发组所有角色分配本资源的权限
					if(!ObjectUtils.isEmpty(roleList)) {
						
						for(Role role:roleList) {
							RoleResource roleResource=new RoleResource();
							
							roleResource.setRoleId(role.getId());
							roleResource.setResourceId(resourceId);
							
							roleResourceService.insertOrUpdate(roleResource);
						}
					}
				}
			});
		}

	}

}
