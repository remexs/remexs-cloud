package com.remexs.data.mongodb.service;

import java.util.Collection;
import java.util.List;

import com.remexs.common.dto.Dto;
import com.remexs.common.vo.PageVO;


public interface MongoService<T> {
	 /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     * @return boolean
     */
    void insert(T entity);

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象列表
     * @return boolean
     */
    void insertBatch(List<T> entityList);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     * @return boolean
     */
    void deleteById(String id);

    /**
     * 根据 whereParams 条件，删除记录
     *
     * @param whereParams 实体包装类 {@link Wrapper}
     * @return boolean
     */
    void delete(Dto whereParams);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     * @return boolean
     */
    void deleteBatchIds(Collection<String> idList);

    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     * @return boolean
     */
    void updateById(T entity);


    /**
     * 根据 whereParams 条件，更新记录
     *
     * @param entity  实体对象
     * @param wrapper 实体包装类 {@link Wrapper}
     * @return boolean
     */
    void update(T entity,Dto whereParams);

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象列表
     * @return boolean
     */
    void updateBatchById(List<T> entityList);


    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return T
     */
    T selectById(String id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     * @return List<T>
     */
    List<T> selectBatchIds(Collection<String> idList);

    /**
     * 根据 whereParams，查询一条记录
     *
     * @param whereParams 实体对象
     * @return T
     */
    T selectOne(Dto whereParams);

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
	Long count(Dto params);
	
    /**
	 * 根据查询条件分页结果
	 * 
	 * @param page
	 * @return
	 */
	PageVO<T> page(PageVO<T> page);


}
