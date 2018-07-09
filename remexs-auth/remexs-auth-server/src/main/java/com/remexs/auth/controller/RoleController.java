package com.remexs.auth.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.entity.Role;
import com.remexs.auth.service.RoleService;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@RestController
@RequestMapping("/role")
public class RoleController  extends MybatisController<RoleService, Role>{

}

