package com.remexs.auth.client.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remexs.auth.entity.Role;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;

@FeignClient(value = "remexs-auth")
public interface RoleFeignService {

	/**
	 * 新增服务资源权限
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.PATCH)
	Result<List<Role>> list(@RequestBody HashDto params);
	
	
	
}
