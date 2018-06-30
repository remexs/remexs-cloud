package com.remexs.auth.client.runner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.remexs.auth.client.feign.ResourceService;
import com.remexs.auth.client.feign.RoleService;
import com.remexs.auth.client.feign.UserService;
import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.Role;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.utils.SpringUtils;

/**
 * 自动系统资源
 * 
 * @author remexs
 *
 */
@Configuration
public class AuthClientRunner implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(AuthClientRunner.class);

	@Override
	public void run(String... arg0) throws Exception {

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
					RoleService roleService = SpringUtils.getBean(RoleService.class);
					roleService.add(role);
				}

				apiMethodCode = apiCode + ":" + apiMethodCode;

				apiMethodPath = apiPath + ("".equals(apiMethodPath) ? "" : (apiMethodPath.contains("/") ? apiMethodPath : "/" + apiMethodPath));

				resource.setName(apiMethodName);
				resource.setCode(apiMethodCode);
				resource.setMethod(apiMethodMethod);
				resource.setType("uri");
				resource.setPath(apiMethodPath);
				ResourceService resourceService = SpringUtils.getBean(ResourceService.class);
				resourceService.add(resource);
			}
		});
	}

}
