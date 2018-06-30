package com.remexs.auth.service.impl;

import com.remexs.auth.entity.RoleResource;
import com.remexs.auth.dao.RoleResourceDao;
import com.remexs.auth.service.RoleResourceService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色资源 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Service
public class RoleResourceServiceImpl extends MybatisServiceImpl<RoleResourceDao, RoleResource> implements RoleResourceService {

}
