package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_user_role")
public class UserRole extends MybatisEntity<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private String roleId;
    @TableField("user_id")
    private String userId;


}
