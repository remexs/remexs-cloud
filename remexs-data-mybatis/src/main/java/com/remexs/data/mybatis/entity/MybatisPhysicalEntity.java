package com.remexs.data.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物理模型父类
 * 
 * @author remexs
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MybatisPhysicalEntity<T extends Model<T>> extends MybatisEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6892055532426961097L;

	/**
	 * 创建者
	 */
	@TableField(value = "create_id", fill = FieldFill.INSERT)
	public String createId;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	public Date createTime;
	/**
	 * 修改者
	 */
	@TableField(value = "update_id", fill = FieldFill.UPDATE)
	
	public String updateId;
	/**
	 * 修改时间
	 */
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
	public Date updateTime;

}
