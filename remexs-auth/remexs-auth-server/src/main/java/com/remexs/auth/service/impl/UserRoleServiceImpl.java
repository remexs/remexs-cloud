package com.remexs.auth.service.impl;

import com.remexs.auth.entity.UserRole;
import com.remexs.auth.dao.UserRoleDao;
import com.remexs.auth.service.UserRoleService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Service
public class UserRoleServiceImpl extends MybatisServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
