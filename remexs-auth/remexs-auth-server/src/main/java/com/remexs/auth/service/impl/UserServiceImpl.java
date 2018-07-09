package com.remexs.auth.service.impl;

import com.remexs.auth.entity.User;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.dao.UserDao;
import com.remexs.auth.service.UserService;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.JwtTokenUtils;
import com.remexs.common.utils.PasswordUtils;
import com.remexs.common.utils.SpringUtils;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;


import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Service
public class UserServiceImpl extends MybatisServiceImpl<UserDao, User> implements UserService {
	
	@Override
	public String register(String name, String account, String password) {
		if (exitsBy(account)) {
			throw new ServiceException("账号已存在");
		}
		User user = new User();
		user.setAccount(account);
		user.setName(name);
		user.setPassword(PasswordUtils.encoder(password));
		this.insert(user);
		return user.getId();
	}

	@Override
	public String login(String account, String password) {
		if (!exitsBy(account)) {
			throw new ServiceException("账号不存在");
		}
		User user = getBy(account);
		if (PasswordUtils.matches(password, user.getPassword())) {
			throw new ServiceException("你输入的密码有误");
		}
		Dto userToken = Dtos.newDto();
		userToken.put("subject", user.getAccount());
		userToken.put(AuthConstants.CURRENT_USER_ACCOUNT, user.getAccount());
		userToken.put(AuthConstants.CURRENT_USER_ID, user.getId());
		userToken.put(AuthConstants.CURRENT_USER_NAME, user.getName());
		
		AuthServerConfiguration authServerConfiguration = SpringUtils.getBean(AuthServerConfiguration.class);
		try {
			return JwtTokenUtils.generateToken(userToken, authServerConfiguration.getUserTokenPubKey(), authServerConfiguration.getUserTokenExpire());
		} catch (Exception e) {
			logger.error("生产usertoken错误");
			e.printStackTrace();
			throw new ServiceException("你输入的密码有误");
		} finally {
			
		}
	}

	@Override
	public boolean exitsBy(String account) {
		return this.selectCount(Condition.create().eq("account", account)) > 0;
	}

	@Override
	public User getBy(String account) {
		return this.selectOne(Condition.create().eq("account", account));
	}

	@Override
	public Boolean hasPromiseBy(String userId, String resourceCode) {
		return baseMapper.queryPromiseCountByUserIdAndResourceCode(userId, resourceCode)>0;
	}

}
