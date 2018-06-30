package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author remexs
 * @since 2018-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_resource")
public class Resource extends MybatisPhysicalEntity<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型
     */
    private String type;
    /**
     * 资源代码
     */
    private String code;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 资源路径
     */
    private String path;


}
