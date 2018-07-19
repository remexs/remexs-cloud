package com.remexs.auth.client.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remexs.auth.entity.Server;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;


@FeignClient(value = "remexs-auth")
public interface ServerFeignService {

	/**
	 * 注册服务
	 * @return
	 */
	@RequestMapping(value = "/server", method = RequestMethod.POST)
	Result<String> add(@RequestBody Server server);
	/**
	 * 根据条件查询所有服务
	 * @return
	 */
	@RequestMapping(value = "/server", method = RequestMethod.PATCH)
	Result<List<Server>> list(@RequestBody HashDto params);
	
	/**
	 * 判断服务是否有权限访问指定方法
	 * @return
	 */
	@RequestMapping(value = "/server/getConfig", method = RequestMethod.GET)
	Result<HashDto> getConfig();
	

	/**
	 * 链接服务
	 * @return
	 */
	@RequestMapping(value = "/server/connect", method = RequestMethod.POST)
	Result<String> connect(@RequestParam("code") String code,@RequestParam("secret") String secret);
	/**
	 * 根据服务代码和接口代码查询服务是否有权限访问指定接口
	 * @return
	 */
	@RequestMapping(value = "/server/hasPromiseBy", method = RequestMethod.POST)
	Result<Boolean> hasPromiseBy(@RequestParam("serverCode") String serverCode,@RequestParam("recourceCode") String recourceCode);

}
