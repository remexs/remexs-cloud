package com.remexs.auth.service;

import com.remexs.auth.entity.User;
import com.remexs.auth.vo.UserVO;

import java.util.Collection;
import java.util.List;

import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
public interface UserService extends MybatisService<User> {
	/**
	 * 根据编码获得用户级别信息
	 * 
	 * @param id
	 * @return
	 */
	public UserVO selectInfoById(String id);
	/**
	 * 根据编码获得用户级别信息
	 * 
	 * @param id
	 * @return
	 */
	public List<UserVO> selectInfosByIds(Collection<String> idList);

	/**
	 * 登录
	 * 
	 * @param account
	 * @param password
	 * @param vcode
	 * @return
	 */
	public String login(String account, String password, String vcode);

	/**
	 * 注册
	 * 
	 * @return
	 */
	public void register(String name,String account,String password);

	/**
	 * 根据账号获得用户基本信息
	 * 
	 * @param account
	 * @return
	 */
	public UserVO selectInfoByAccount(String account);
	/**
	 * 根据账号获得用户基本信息
	 * 
	 * @param account
	 * @return
	 */
	public List<UserVO> selectInfosByAccounts(List<String> accounts);

	/**
	 * 判断账号是否存在
	 * 
	 * @param account
	 * @return
	 */
	public boolean existAccount(String account);

}
