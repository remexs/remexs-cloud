package com.remexs.auth.service;

import com.remexs.auth.entity.User;
import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
public interface UserService extends MybatisService<User> {
	/**
	 * 注册
	 * @param name
	 * @param password
	 * @param account
	 * @return
	 */
	public String register(String name,String account,String password);
	/**
	 * 注册
	 * @param name
	 * @param password
	 * @param account
	 * @return
	 */
	public String login(String account,String password);
	/**
	 * 根据账号查询用户是否存在
	 * @param account
	 * @return
	 */
	public boolean exitsBy(String account);
	/**
	 * 
	 * @param account
	 * @return
	 */
	public User getBy(String account);
	/**
	 * 根据用户编码和资源代码查询用户是否拥护资源权限
	 * @param userId
	 * @param resourceCode
	 */
	public Boolean hasPromiseBy(String userId,String resourceCode);
}
