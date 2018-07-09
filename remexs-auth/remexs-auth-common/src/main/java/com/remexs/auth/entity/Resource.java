package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 服务接口资源实体
 * </p>
 *
 * @author remexs
 * @since 2018-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_resource")
public class Resource extends MybatisPhysicalEntity<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务编码
     */
    @TableField("server_id")
    private String serverId;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 接口代码
     */
    private String code;
    /**
     * 接口请求方式
     */
    private String method;

    /**
     * 接口路径
     */
    private String path;
    /**
     * 是否锁定
     */
    private Integer locked;


}
