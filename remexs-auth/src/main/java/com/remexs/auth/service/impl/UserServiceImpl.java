package com.remexs.auth.service.impl;

import com.remexs.auth.entity.User;
import com.remexs.auth.dao.UserDao;
import com.remexs.auth.service.UserService;
import com.remexs.auth.vo.UserVO;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.common.exception.ServiceException;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Service
public class UserServiceImpl extends MybatisServiceImpl<UserDao, User> implements UserService {

	@Override
	public UserVO selectInfoById(String id) {
		return new UserVO().copyFrom(selectById(id));
	}

	@Override
	public String login(String account, String password, String vcode) {
		String token = null;
		return token;
	}

	public UserVO selectInfoByAccount(String account) {
		return new UserVO().copyFrom(this.selectOne(Condition.create().eq("account", account)));
	}

	public boolean existAccount(String account) {
		return this.selectCount(Condition.create().eq("account", account)) > 0;
	}

	@Override
	public void register(String name, String account, String password) {
		if (existAccount(account)) {
			throw new ServiceException("账号已存在");
		}
		User user = new User();
		user.setName(name);
		user.setAccount(account);
		user.setPassword(password);
		this.insert(user);
	}

	@Override
	public List<UserVO> selectInfosByIds(Collection<String> idList) {
		List<UserVO> userVOs = new ArrayList<>();
		this.selectBatchIds(idList).iterator().forEachRemaining(user -> {
			userVOs.add(new UserVO().copyFrom(user));
		});
		return userVOs;
	}

	@Override
	public List<UserVO> selectInfosByAccounts(List<String> accounts) {
		List<UserVO> userList = new ArrayList<>();
		this.selectList(Condition.create().in("account", accounts)).iterator().forEachRemaining(user -> {
			userList.add(new UserVO().copyFrom(user));
		});
		return userList;
	}
}
