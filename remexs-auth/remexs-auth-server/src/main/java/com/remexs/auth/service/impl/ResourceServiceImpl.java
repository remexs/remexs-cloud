package com.remexs.auth.service.impl;

import com.remexs.auth.entity.Resource;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.auth.dao.ResourceDao;
import com.remexs.auth.service.ResourceService;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务API接口分配 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-07-04
 */
@Service
public class ResourceServiceImpl extends MybatisServiceImpl<ResourceDao, Resource> implements ResourceService {

	@Override
	public boolean insertOrUpdate(Resource resource) {
		String code=resource.getCode();
		if(existBy(code)) {
			this.update(resource,Condition.create().eq("code",code ));
			resource.copyFrom(this.getBy(resource.getCode()));
			return true;
		}else {
			return this.insert(resource);
		}
	}

	@Override
	public Resource getBy(String code) {
		return selectOne(Condition.create().eq("code", code));
	}

	@Override
	public boolean existBy(String code) {
		return this.selectCount(Condition.create().eq("code", code))>0;
	}
}
