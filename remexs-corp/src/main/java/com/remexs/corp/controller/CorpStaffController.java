package com.remexs.corp.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.corp.entity.CorpStaff;
import com.remexs.corp.service.CorpStaffService;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 公司员工 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@RestController
@RequestMapping("/corp/staff")
public class CorpStaffController extends MybatisController<CorpStaffService, CorpStaff>{

}

