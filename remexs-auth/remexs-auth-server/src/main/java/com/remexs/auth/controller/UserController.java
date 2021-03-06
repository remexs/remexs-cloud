package com.remexs.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.context.AuthContext;
import com.remexs.auth.entity.User;
import com.remexs.auth.service.UserService;
import com.remexs.auth.vo.UserVO;
import com.remexs.common.annotation.ApiFilter;
import com.remexs.common.annotation.ApiMethodFilter;
import com.remexs.common.response.Result;
import com.remexs.common.response.ResultUtils;
import com.remexs.data.mybatis.controller.MybatisController;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@RestController
@RequestMapping("/user")
@ApiFilter(name = "用户接口", code = "user", path = "/user")
public class UserController extends MybatisController<UserService, User> {
	@Autowired
	UserService userService;

	/**
	 * 
	 * @param name
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ApiMethodFilter(name = "用户注册", code = "register", method = "POST", path = "/register")
	public Result<String> register(@RequestParam String name, String account, @RequestParam String password) {
		String userId = userService.register(name, password, account);
		return ResultUtils.ok(userId);
	}

	/**
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiMethodFilter(name = "用户登陆", code = "login", method = "POST", path = "/login")
	public Result<String> login(@RequestParam String account, @RequestParam String password) {
		String token = userService.login(account, password);
		return ResultUtils.ok(token);
	}

	/**
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ApiMethodFilter(name = "用户登陆", code = "info", method = "GET", path = "/info", userTokenFilter = true)
	public Result<UserVO> info() {
		String userId = AuthContext.get(AuthConstants.CURRENT_USER_ID).toString();
		UserVO userVO = baseService.info(userId);
		return ResultUtils.ok(userVO);
	}
}
