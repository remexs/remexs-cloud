package com.remexs.corp.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.common.annotation.ApiFilter;
import com.remexs.corp.entity.Department;
import com.remexs.corp.service.DepartmentService;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 公司部门 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@RestController
@RequestMapping("/department")
@ApiFilter(name = "公司部门", code = "company", path = "/company")
public class DepartmentController extends MybatisController<DepartmentService, Department>{

}

