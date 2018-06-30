package com.remexs.corp.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.corp.entity.CorpDept;
import com.remexs.corp.service.CorpDeptService;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 公司部门 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@RestController
@RequestMapping("/corp/dept")
public class CorpDeptController extends MybatisController<CorpDeptService, CorpDept>{

}

