package com.remexs.auth.client.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remexs.auth.entity.Resource;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;

/**
 * 资源客户端接口
 * 
 * @author remexs
 *
 */
@FeignClient(value = "remexs-auth")
public interface ResourceFeignService {
	/**
	 * 根据资源编码获得资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	Result<Resource> get(@PathVariable("id") String id);

	/**
	 * 新增资源
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	Result<String> add(@RequestBody Resource serverApi);
	/**
	 * 根据条件查询资源列表
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping(value = "/resource", method = RequestMethod.PATCH)
	Result<List<Resource>> list(@RequestBody HashDto params);
}
