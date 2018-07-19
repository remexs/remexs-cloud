package com.remexs.auth.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.entity.RoleResource;
import com.remexs.auth.service.RoleResourceService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.data.mybatis.controller.MybatisController;


/**
 * <p>
 * 角色服务关联表 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@RestController
@RequestMapping("/role/resource")
@ApiFilter(name = "角色资源接口", code = "role:resource", path = "/role/resource")
public class RoleResourceController extends MybatisController<RoleResourceService, RoleResource> {

	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiMethodFilter(name = "新增", code = "add", method = "POST", path = "")
	public Result<String> add(@RequestBody RoleResource roleResource) {
		baseService.insertOrUpdate(roleResource);
		return ResultUtils.ok(roleResource.getId());
	}

}
