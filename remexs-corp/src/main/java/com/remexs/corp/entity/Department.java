package com.remexs.corp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.PhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公司部门
 * </p>
 *
 * @author remexs
 * @since 2018-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)

@TableName("re_corp_dept")
public class CorpDept extends PhysicalEntity<CorpDept> {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 上级部门
	 */
	@TableField("parent_id")
	private String parentId;
	/**
	 * 公司编码
	 */
	@TableField("corp_id")
	private String corpId;
	/**
	 * 是否有子部门
	 */
	@TableField("has_children")
	private Integer hasChildren;
	/**
	 * 部门管理者
	 */
	@TableField("manager_id")
	private String managerId;

}
