package com.remexs.corp.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.common.annotation.ApiFilter;
import com.remexs.corp.entity.Employee;
import com.remexs.corp.service.EmployeeService;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 公司用户 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@RestController
@RequestMapping("/employee")
@ApiFilter(name = "公司用户 ", code = "employee", path = "/employee")
public class EmployeeController extends MybatisController<EmployeeService, Employee>{

}

