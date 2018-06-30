package com.remexs.data.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

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
public class PhysicalEntity<T extends Model<T>> extends MybatisEntity<T> {

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
	public Date updateTime;

	@Override
	public Serializable pkVal() {
		return this.id;
	}

}
