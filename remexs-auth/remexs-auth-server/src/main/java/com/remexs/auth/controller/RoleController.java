package com.remexs.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.entity.Role;
import com.remexs.auth.service.RoleService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.data.mybatis.controller.MybatisController;

import io.swagger.annotations.Api;
/**
 * 角色 前端控制器
 * @author remexs
 *
 */
@Api
@RestController
@RequestMapping("/role")
@ApiFilter(name="用户接口",code="role",path="/role")
public class RoleController extends MybatisController<RoleService, Role>{

}
