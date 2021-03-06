package com.remexs.corp.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.remexs.data.mybatis.entity.MybatisPhysicalEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司
 * </p>
 *
 * @author remexs
 * @since 2018-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("re_company")
public class Company extends MybatisPhysicalEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 公司名称
     */
    private String name;
    /**
     * 秘钥
     */
    private String secret;


}
