package com.remexs.auth.service.impl;

import com.remexs.auth.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.remexs.auth.dao.RoleDao;
import com.remexs.auth.service.RoleService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Service
public class RoleServiceImpl extends MybatisServiceImpl<RoleDao, Role> implements RoleService {

	@Override
	public List<Role> queryListByUserId(String userId) {
		return baseMapper.queryListByUserId(userId);
	}

}
