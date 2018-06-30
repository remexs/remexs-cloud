package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
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


}
