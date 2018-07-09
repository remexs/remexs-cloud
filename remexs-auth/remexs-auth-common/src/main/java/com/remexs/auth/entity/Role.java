package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_role")
public class Role extends MybatisPhysicalEntity<Role> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色代码
	 */
	private String code;
	/**
	 * 角色类型
	 */
	private Integer type;
	/**
	 * 父类编码
	 */
	@TableField("parent_id")
	private String parentId;
}
