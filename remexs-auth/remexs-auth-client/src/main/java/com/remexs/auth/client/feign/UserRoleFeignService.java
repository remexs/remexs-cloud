package com.remexs.auth.client.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remexs.auth.entity.UserRole;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;

@FeignClient(value = "remexs-auth")
public interface UserRoleFeignService {

	/**
	 * 根据条件查询用户角色集合
	 * @return
	 */
	@RequestMapping(value = "/user/role", method = RequestMethod.GET)
	Result<List<UserRole>> list(@RequestBody HashDto params);
}
