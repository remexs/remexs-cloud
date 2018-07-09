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
 * 服务接口权限分配
 * </p>
 *
 * @author remexs
 * @since 2018-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_server_resource")
public class ServerResource extends MybatisEntity<ServerResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端编码
     */
    @TableField("server_id")
    private String serverId;
    /**
     * 客户端分配的接口编码
     */
    @TableField("resource_id")
    private String resourceId;


}
