package com.remexs.corp.service.impl;

import com.remexs.corp.entity.Corp;
import com.remexs.corp.entity.CorpStaff;
import com.remexs.corp.dao.CorpDao;
import com.remexs.corp.service.CorpService;
import com.remexs.corp.service.CorpStaffService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.remexs.data.mybatis.service.impl.MybatisServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司 服务实现类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Service
public class CorpServiceImpl extends MybatisServiceImpl<CorpDao, Corp> implements CorpService {
	@Autowired 
	CorpStaffService corpStaffService;
	@Override
	public List<Corp> getListByUserAccount(String account) {
		List<CorpStaff> staffList=corpStaffService.selectList(Condition.create().eq("account", account));
		List<String> corpIds=new ArrayList<>();
		staffList.iterator().forEachRemaining(corpStaff->{
			corpIds.add(corpStaff.getId());
		});
		return this.selectBatchIds(corpIds);
	}
}
