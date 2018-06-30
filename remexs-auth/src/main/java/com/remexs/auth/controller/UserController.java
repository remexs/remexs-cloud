package com.remexs.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.common.controller.BaseController;
import com.remexs.common.dto.impl.HashDto;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.auth.entity.User;
import com.remexs.auth.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api(description = "用户接口")
public class UserController extends BaseController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<String> login(
		@ApiParam(required=true,value="用户账号")	@RequestParam String account,
		@ApiParam(required=true,value="用户密码")	@RequestParam String password,
		@ApiParam(required=true,value="验证码")	@RequestParam String vcode
	) {
		String token = userService.login(account, password, vcode);
		return ResultUtils.ok(token);
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result<String> register(
		@ApiParam(required=true,value="用户名")	@RequestParam String name,
		@ApiParam(required=true,value="用户账号")	@RequestParam String account,
		@ApiParam(required=true,value="用户密码")	@RequestParam String password
		
	) {
		userService.register(name, account, password);
		return ResultUtils.ok();
	}
	
	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	public Result<Boolean> register(
		@ApiParam(required=true,value="用户账号")	@RequestParam String account
		
	) {
		return ResultUtils.ok(userService.existAccount(account));
	}
	@RequestMapping(value = "/list", method = RequestMethod.PATCH)
	public Result<List<User>> list(
		@RequestBody(required=false) HashDto params,
		@RequestBody(required=false) HashDto sorts
		
	) {
		return ResultUtils.ok(userService.list(params, sorts));
	}
}
