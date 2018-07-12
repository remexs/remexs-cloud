package com.remexs.corp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.client.feign.UserFeignService;
import com.remexs.auth.entity.User;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.corp.entity.Company;
import com.remexs.corp.service.CompanyService;
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
	@Autowired
	UserFeignService userFeignService;
	
	/**
	 * 
	 * @param paramsDto
	 * @param sortsDto
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.PATCH)
	@ApiMethodFilter(name = "获得用户列表", code = "users", method = "PATCH", path = "/users")
	public Result<List<User>> users(@RequestBody(required = false) HashDto paramsDto) {
		return userFeignService.list(paramsDto);
	}


}
