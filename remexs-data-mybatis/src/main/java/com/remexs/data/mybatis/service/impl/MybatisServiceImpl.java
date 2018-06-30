package com.remexs.data.mybatis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.remexs.common.dto.Dto;
import com.remexs.data.mybatis.constants.SearchCons;
import com.remexs.data.mybatis.service.MybatisService;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.ReflectionUtils;
import com.remexs.common.vo.PageVO;

/**
 * 实体业务逻辑基础类
 * 
 * @author remexs
 */
public class MybatisServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements MybatisService<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	protected Class<T> currentModelClass() {
		return ReflectionUtils.getSuperClassGenricType(getClass(), 1);
	}
	
	/**
	 * 是否加载 查询条件
	 *
	 * @param column 	查询的列
	 * @param key 		参数键
	 * @param value  	参数值
	 * @return
	 */
	private boolean isLoadCond(String column, String key, Object value) {
		return key.startsWith(column) && !ObjectUtils.isEmpty(value);
	}

	/**
	 * 加载 搜索条件 
	 *
	 * @param searchParams 
	 * @param condition
	 * @example 查询 年龄大于20 小于30 的用户
	 *  params {
	 *  	great_age	:20,
	 *  	less_age	:30
	 *  }
	 */
	private void loadSearchParam(Dto searchParams, Condition condition) {

		if (null != searchParams) {
			searchParams.forEach((key, value) -> {
				if (isLoadCond(SearchCons.EQ, key, value)) {//等于
					condition.eq(key.split(SearchCons.EQ)[1], value);
				} else if (isLoadCond(SearchCons.NE, key, value)) {//不等于
					condition.ne(key.split(SearchCons.NE)[1], value);
				} else if (isLoadCond(SearchCons.GREAT, key, value)) {//大于
					condition.ge(key.split(SearchCons.GREAT)[1], value);
				} else if (isLoadCond(SearchCons.LESS, key, value)) {//小于
					condition.le(key.split(SearchCons.LESS)[1], String.valueOf(value));
				} else if (isLoadCond(SearchCons.IN, key, value)) {//包含
					condition.in(key.split(SearchCons.IN)[1], String.valueOf(value));
				} else if (isLoadCond(SearchCons.NOT_IN, key, value)) {//不包含
					condition.notIn(key.split(SearchCons.NOT_IN)[1], String.valueOf(value));
				} else if (isLoadCond(SearchCons.L_LIKE, key, value)) {
					condition.like(key.split(SearchCons.L_LIKE)[1], String.valueOf(value), SqlLike.LEFT);
				} else if (isLoadCond(SearchCons.R_LIKE, key, value)) {
					condition.like(key.split(SearchCons.R_LIKE)[1], String.valueOf(value), SqlLike.RIGHT);
				} else if (isLoadCond(SearchCons.LIKE, key, value)) {
					condition.like(key.split(SearchCons.LIKE)[1], String.valueOf(value));
				}

			});
		}
	}

	/**
	 * 加载 排序条件
	 * @example  根据年龄和姓名排序
	 * sorts{
	 * 	 age:asc,
	 * 	 name:desc
	 * }
	 */
	private void loadSort(Dto sorts, Condition condition) {
		if (null != sorts && sorts.size() > 0) {
			StringBuffer stringBuffer = new StringBuffer();
			sorts.forEach((key, value) -> {
				stringBuffer.append(key);
				stringBuffer.append("asc".equals(value.toString().toLowerCase()) ? " ASC , " : " DESC ,");
			});
			condition.orderBy(stringBuffer.toString().trim().substring(0, stringBuffer.length() - 1));
		}
	}
	
	@Override
	public PageVO<T> page(PageVO<T> pageVO) {
		Page<T> page = new Page<T>(pageVO.getPage(), pageVO.getLimit());
		Condition condition = new Condition();
		loadSearchParam(pageVO.getSearchParams(), condition);
		loadSort(pageVO.getSorts(), condition);
		selectPage(page, condition);
		pageVO.setTotal(page.getTotal());
		pageVO.setList(page.getRecords());
		return pageVO;
	}

	@Override
	public List<T> list(Dto params, Dto sorts) {
		Condition condition = new Condition();
		loadSearchParam(params, condition);
		loadSort(sorts, condition);
		return selectList(condition);
	}

	@Override
	public int count(Dto params) {
		Condition condition = new Condition();
		loadSearchParam(params, condition);
		return selectCount(condition);
	}
}
