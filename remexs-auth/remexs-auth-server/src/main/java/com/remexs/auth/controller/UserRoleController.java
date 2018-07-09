package com.remexs.auth.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.entity.UserRole;
import com.remexs.auth.service.UserRoleService;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@RestController
@RequestMapping("/user/role")
@ApiFilter(code = "user:role", path = "/user/role")
public class UserRoleController extends MybatisController<UserRoleService, UserRole>{
	
	
}

