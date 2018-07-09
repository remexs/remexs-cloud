package com.remexs.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统服务实体
 * </p>
 *
 * @author remexs
 * @since 2018-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_server")
public class Server extends MybatisPhysicalEntity<Server> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务名
     */
    private String name;
    private String code;
    /**
     * 服务密钥
     */
    private String secret;
    /**
     * 服务描述
     */
    private String desc;


}
