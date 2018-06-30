package com.remexs.auth.service.impl;

import com.remexs.auth.entity.User;
import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.auth.dao.UserDao;
import com.remexs.auth.service.UserService;
import com.remexs.auth.vo.UserVO;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.exception.ErrCode;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.JwtTokenUtils;
import com.remexs.common.utils.PasswordUtils;
import com.remexs.common.utils.SpringUtils;
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
	public String login(String account, String password) throws Exception {
		AuthServerConfiguration authServerConfiguration =SpringUtils.getBean(AuthServerConfiguration.class);
		if(!existAccount(account)) {
			throw new ServiceException(ErrCode.USERNAME_OR_PASSWORD_ERR.getCode(),ErrCode.USERNAME_OR_PASSWORD_ERR.getMsg());
		}
		
		User user=getUserByAccount(account);
		
		if(!PasswordUtils.matches(password, user.getPassword())) {
			throw new ServiceException(ErrCode.USERNAME_OR_PASSWORD_ERR.getCode(),ErrCode.USERNAME_OR_PASSWORD_ERR.getMsg());
		}
		Dto jwtInfo=Dtos.newDto();
		jwtInfo.put("subject", user.getAccount());
		jwtInfo.put("userId", user.getId());
		jwtInfo.put("userName", user.getName());
		String token = JwtTokenUtils.generateToken(jwtInfo, authServerConfiguration.getUserTokenPriKey(), authServerConfiguration.getUserTokenExpire());
		
		return token;
	}
	
	public User getUserByAccount(String account) {
		return this.selectOne(Condition.create().eq("account", account));
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

}
