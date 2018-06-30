package com.remexs.company.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司部门
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("re_department")
public class Department extends MybatisPhysicalEntity {

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
    @TableField("company_id")
    private String companyId;
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
