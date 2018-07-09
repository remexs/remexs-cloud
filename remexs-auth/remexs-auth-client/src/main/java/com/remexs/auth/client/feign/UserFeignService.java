package com.remexs.auth.client.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remexs.auth.entity.User;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;

@FeignClient(value = "remexs-auth")
public interface UserFeignService {
	/**
	 * 根据服务代码和接口代码查询服务是否有权限访问指定接口
	 * @return
	 */
	@RequestMapping(value = "/user/hasPromiseBy", method = RequestMethod.POST)
	Result<Boolean> hasPromiseBy(@RequestParam("userId") String userId,@RequestParam("recourceCode") String recourceCode);
	
	/**
	 * 新增服务资源权限
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.PATCH)
	Result<List<User>> list(@RequestBody HashDto params);
	
	
	

}
