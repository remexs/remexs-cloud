package com.remexs.auth.service;

import com.remexs.auth.entity.User;
import com.remexs.auth.vo.UserVO;

import java.util.Collection;
import java.util.List;

import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
public interface UserService extends MybatisService<User> {
	

	/**
	 * 登录
	 * 
	 * @param account
	 * @param password
	 * @param vcode
	 * @return
	 */
	public String login(String account, String password) throws Exception;

	/**
	 * 注册
	 * 
	 * @return
	 */
	public void register(String name, String account, String password);

	/**
	 * 判断账号是否存在
	 * 
	 * @param account
	 * @return
	 */
	public boolean existAccount(String account);

}
