package com.remexs.auth.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.ServerResource;
import com.remexs.auth.service.ServerResourceService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.data.mybatis.controller.MybatisController;

import io.swagger.annotations.Api;

/**
 * <p>
 * 服务资源前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Api
@RestController
@RequestMapping("/server/resource")
@ApiFilter(name = "服务资源", code = "server:resource", path = "/server/resource")
public class ServerResourceController extends MybatisController<ServerResourceService, ServerResource> {

	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiMethodFilter(name = "新增", code = "add", method = "POST", path = "")
	public Result<String> add(@RequestBody ServerResource serverResource) {
		baseService.insertOrUpdate(serverResource);
		return ResultUtils.ok(serverResource.getId());
	}
}
