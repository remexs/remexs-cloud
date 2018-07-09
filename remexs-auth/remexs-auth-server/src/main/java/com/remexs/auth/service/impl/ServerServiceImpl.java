package com.remexs.auth.service.impl;

import com.remexs.auth.entity.Resource;
import com.remexs.auth.entity.Server;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.auth.configuration.AuthServerConfiguration;
import com.remexs.auth.constants.AuthConstants;
import com.remexs.auth.dao.ServerDao;
import com.remexs.auth.service.ResourceService;
import com.remexs.auth.service.ServerService;
import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.exception.ServiceException;
import com.remexs.common.utils.JwtTokenUtils;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.SpringUtils;
import com.remexs.common.utils.StringUtils;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-03
 */
@Service
public class ServerServiceImpl extends MybatisServiceImpl<ServerDao, Server> implements ServerService {
	@Autowired
	ResourceService resourceService;
	@Override
	public boolean insertOrUpdate(Server server) {
		String code=server.getCode();
		if (existBy(code)) {
			this.update(server, Condition.create().eq("code", code));
			server.copyFrom(this.getBy(code));
			return true;
		}else {
			server.setSecret(StringUtils.generateStr(8));
			return this.insert(server);
		}
	}

	@Override
	public String connect(String code, String secret)  {
		if (!existBy(code)) {
			throw new ServiceException("服务不存在或服务被锁定");
		}
		Server server = getBy(code);
		if (!server.getSecret().equals(secret)) {
			throw new ServiceException("服务密钥错误");
		}

		Dto clientToken = Dtos.newDto();
		clientToken.put("subject", server.getCode());
		clientToken.put(AuthConstants.CURRENT_CLIENT_ID, server.getId());
		clientToken.put(AuthConstants.CURRENT_CLIENT_CODE, server.getCode());

		AuthServerConfiguration authServerConfiguration = SpringUtils.getBean(AuthServerConfiguration.class);
		String token = null;
		try {
			token = JwtTokenUtils.generateToken(clientToken, authServerConfiguration.getClientTokenPriKey(), authServerConfiguration.getClientTokenExpire());
		} catch (Exception e) {
			throw new ServiceException("token工厂生产token失败");
		}
		return token;

	}

	@Override
	public Boolean existBy(String code) {
		return this.selectCount(Condition.create().eq("code", code)) > 0;
	}

	@Override
	public Server getBy(String code) {
		return this.selectOne(Condition.create().eq("code", code));
	}

	@Override
	public String getSecretBy(String id) {
		Server server = this.selectById(id);
		return server.getSecret();

	}

	@Override
	public boolean hasPromiseBy(String serverCode, String resourceCode) {
		Server server=getBy(serverCode);
		Resource resource=resourceService.getBy(resourceCode);
		if(ObjectUtils.isEmpty(server)) {
			throw new ServiceException("服务不存在！");
		}
		if(ObjectUtils.isEmpty(resource)) {
			throw new ServiceException("接口不存在！");
		}
		return this.selectCount(Condition.create().eq("server_id",server.getId()).and().eq("resource_id", resource.getId()))>0;
	}

}
