package com.remexs.auth.client.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remexs.auth.entity.RoleResource;
import com.remexs.common.response.Result;

@FeignClient(value = "remexs-auth")
public interface RoleResourceFeignService {
	/**
	 * 新增资源权限
	 * @return
	 */
	@RequestMapping(value = "/role/resource", method = RequestMethod.POST)
	Result<String> add(@RequestBody RoleResource roleResource);
	
}
