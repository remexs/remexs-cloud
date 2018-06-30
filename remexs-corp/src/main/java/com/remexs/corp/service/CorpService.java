package com.remexs.corp.service;

import com.remexs.corp.entity.Corp;

import java.util.List;

import com.remexs.data.mybatis.service.MybatisService;

/**
 * <p>
 * 公司 服务类
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
public interface CorpService extends MybatisService<Corp> {
	/**
	 * 根据用户账号查询用户所在公司列表
	 * @param account
	 * @return
	 */
	public List<Corp> getListByUserAccount(String account);
}
