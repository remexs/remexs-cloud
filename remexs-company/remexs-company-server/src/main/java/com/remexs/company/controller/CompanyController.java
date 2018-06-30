package com.remexs.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.remexs.common.annotation.ApiFilter;
import com.remexs.company.entity.Company;
import com.remexs.company.service.CompanyService;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 公司 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@RestController
@RequestMapping("/company")
@ApiFilter(name = "公司 ", code = "company", path = "/company")
public class CompanyController extends MybatisController<CompanyService, Company> {

}
