package com.remexs.auth.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remexs.auth.entity.User;
import com.remexs.common.response.Result;

/**
 * auth 客户端 用户接口
 * @author remexs
 *
 */
@FeignClient(value = "remexs-auth")
public interface UserService {
	/**
	 * 根据用户编码获得用户信息
	 * @param id 用户编码
	 * @return
	 */
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
	Result<User> get(@PathVariable("id") String id);
	
}
