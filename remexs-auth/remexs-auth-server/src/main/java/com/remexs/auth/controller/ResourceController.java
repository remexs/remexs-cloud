package com.remexs.auth.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.entity.Resource;
import com.remexs.auth.service.ResourceService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.data.mybatis.controller.MybatisController;


/**
 * <p>
 * 服务接口 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@RestController
@RequestMapping("/resource")
@ApiFilter(name = "资源接口", code = "resource", path = "/resource")
public class ResourceController extends MybatisController<ResourceService, Resource> {

	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiMethodFilter(name = "新增", code = "add", method = "POST", path = "")
	public Result<String> add(@RequestBody Resource resource) {
		baseService.insertOrUpdate(resource);
		resource = baseService.getBy(resource.getCode());
		return ResultUtils.ok(resource.getId());
	}

}
