package com.remexs.auth.controller;


import com.remexs.data.mybatis.controller.MybatisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.auth.entity.User;
import com.remexs.auth.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api
@ApiFilter(name="用户接口",code="user",path="/user")
public class UserController extends MybatisController<UserService,User>{
	@Autowired
	UserService userService;
	
	@ApiMethodFilter(name="用户登陆",code="login",path="/login",userToken=false,clientToken=false)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<String> login(
		@ApiParam(required=true,value="用户账号")	@RequestParam String account,
		@ApiParam(required=true,value="用户密码")	@RequestParam String password,
		@ApiParam(required=true,value="验证码")	@RequestParam String vcode
	) throws Exception {
		String token = userService.login(account, password);
		return ResultUtils.ok(token);
	}
	@ApiMethodFilter(name="用户注册",code="register",path="/register",userToken=false,clientToken=false)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result<String> register(
		@ApiParam(required=true,value="用户名")	@RequestParam String name,
		@ApiParam(required=true,value="用户账号")	@RequestParam String account,
		@ApiParam(required=true,value="用户密码")	@RequestParam String password
		
	) {
		userService.register(name, account, password);
		return ResultUtils.ok();
	}
	@ApiMethodFilter(name="用户验证",code="validate",path="/validate",userToken=false,clientToken=false)
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Result<Boolean> valid(
		@ApiParam(required=true,value="用户账号")	@RequestParam String account
		
	) {
		return ResultUtils.ok(userService.existAccount(account));
	}

}
