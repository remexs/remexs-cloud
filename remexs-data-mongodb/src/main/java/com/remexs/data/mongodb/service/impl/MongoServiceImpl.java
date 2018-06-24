package com.remexs.data.mongodb.service.impl;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.remexs.common.dto.Dto;
import com.remexs.data.mongodb.dao.MongoDao;
import com.remexs.data.mongodb.entity.MongoEntity;
import com.remexs.data.mongodb.service.MongoService;
import com.remexs.data.mongodb.constant.SearchCons;
import com.remexs.common.utils.ObjectUtils;
import com.remexs.common.utils.ReflectionUtils;
import com.remexs.common.vo.PageVO;

/**
 * mogodb 公共service 
 * @author remexs
 *
 * @param <M>
 * @param <T>
 */
public class MongoServiceImpl<M extends MongoDao<T>, T extends MongoEntity<T>> implements MongoService<T> {
	@Autowired
	protected M modelDao;
	@Autowired
	MongoTemplate mongoTemplate;
	
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
	private void loadSearchParam(Dto searchParams, Criteria criteria) {

		if (null != searchParams) {
			searchParams.forEach((key, value) -> {
				if (isLoadCond(SearchCons.EQ, key, value)) {// 等于
					criteria.and(key.split(SearchCons.EQ)[1]).is(value);
				} else if (isLoadCond(SearchCons.NE, key, value)) {// 不等于
					criteria.and(key.split(SearchCons.NE)[1]).ne(value);
				} else if (isLoadCond(SearchCons.GREAT, key, value)) {// 大于
					criteria.and(key.split(SearchCons.GREAT)[1]).gt(value);
				} else if (isLoadCond(SearchCons.LESS, key, value)) {// 小于
					criteria.and(key.split(SearchCons.LESS)[1]).lt(value);
				}else if (isLoadCond(SearchCons.IN, key, value)) {// 包含
					if (value instanceof Character) {
						criteria.and(key.split(SearchCons.IN)[1]).in(value.toString().split(","));
					} else {
						criteria.and(key.split(SearchCons.IN)[1]).in(value.toString().split(","));
					}
				} else if (isLoadCond(SearchCons.NOT_IN, key, value)) {// 不包含
					if (value instanceof Character) {
						criteria.and(key.split(SearchCons.NOT_IN)[1]).nin(value.toString().split(","));
					} else {
						criteria.and(key.split(SearchCons.NOT_IN)[1]).nin(value);
					}
				} else if (isLoadCond(SearchCons.REGX, key, value)) {
					criteria.and(key.split(SearchCons.REGX)[1]).regex((String)value);
				}
			});
		}
	}
	

	@Override
	public void insert(T entity) {
		modelDao.insert(entity);
	}
	@Override
	public void insertBatch(List<T> entityList) {
		modelDao.insert(entityList);
	}

	@Override
	public void deleteById(String id) {
		modelDao.delete(id);
	}
	
	@Override
	public void deleteBatchIds(Collection<String> idList) {
		for(String id:idList) {
			deleteById(id);
		}
	}

	@Override
	public void delete(Dto whereParams) {
		Criteria criteria=new Criteria();
		loadSearchParam(whereParams, criteria);
		Query query=new Query(criteria);
		mongoTemplate.remove(query, currentModelClass());
	}

	@Override
	public void updateById(T entity) {
		modelDao.save(entity);
	}

	@Override
	public void update(T entity, Dto whereParams) {
		
	}

	@Override
	public void updateBatchById(List<T> entityList) {
		modelDao.save(entityList);
	}

	@Override
	public T selectById(String id) {
		return modelDao.findOne(id);
	}

	@Override
	public List<T> selectBatchIds(Collection<String> idList) {
		// TODO Auto-generated method stub
		List<T> list=new ArrayList<>();
		modelDao.findAll(idList).iterator().forEachRemaining((entity)->{
			list.add(entity);
		});
		return list;
	}

	@Override
	public T selectOne(Dto whereParams) {
		Criteria criteria=new Criteria();
		loadSearchParam(whereParams, criteria);
		Query query=new Query(criteria);
		return mongoTemplate.findOne(query, currentModelClass());
	}

	@Override
	public Long count(Dto whereParams) {
		Criteria criteria=new Criteria();
		loadSearchParam(whereParams, criteria);
		Query query=new Query(criteria);
		return mongoTemplate.count(query, currentModelClass());
	}

	@Override
	public List<T> list(Dto params, Dto sorts) {
		Criteria criteria=new Criteria();
		loadSearchParam(params, criteria);
		Query query=new Query(criteria);
		return mongoTemplate.find(query, currentModelClass());
	}

	@Override
	public PageVO<T> page(PageVO<T> page) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
