package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_user")
public class User extends MybatisPhysicalEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;


}
