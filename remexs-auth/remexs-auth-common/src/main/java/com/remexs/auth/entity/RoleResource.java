package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisEntity;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色资源分配
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_role_resource")
public class RoleResource extends MybatisEntity<RoleResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 资源编码
     */
    @TableField("resource_id")
    private String resourceId;


}
