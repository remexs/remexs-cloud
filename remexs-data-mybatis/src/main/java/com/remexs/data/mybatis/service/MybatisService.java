package com.remexs.data.mybatis.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.remexs.common.dto.Dto;
import com.remexs.common.vo.PageVO;

/**
 *
 * @author remexs
 * @date 2017/12/21
 *       <p>
 *       Email bigdrone@163.com
 *       <p>
 *       Describe:
 */
public interface MybatisService<T> extends IService<T> {
	/**
	 * 根据查询条件分页结果
	 * 
	 * @param page
	 * @return
	 */
	PageVO<T> page(PageVO<T> page);

	/**
	 * 根据条件查询所有结果
	 * @param params
	 * @param sorts
	 * @return
	 * @example 查询 年龄大于20 小于30 的用户
	 *  params {
	 *  	great_age	:20,
	 *  	less_age	:30
	 *  }
	 */
	List<T> list(Dto params, Dto sorts);

	/**
	 * 根据条件查询总数
	 * @param params
	 * @return
	 */
	int count(Dto params);
}
