package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_user_role")
public class UserRole extends MybatisPhysicalEntity<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private String roleId;
    @TableField("user_id")
    private String userId;


}
