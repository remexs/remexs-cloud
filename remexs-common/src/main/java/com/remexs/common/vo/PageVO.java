package com.remexs.common.vo;

import java.io.Serializable;
import java.util.List;

import com.remexs.common.dto.Dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageVO<T> extends BaseVO<T> implements Serializable {
	private static final long serialVersionUID = 2252240868205663450L;
	
	/**
	 * 当前页码
	 */
	private int page;
	
	/**
	 * 当前页码
	 */
	private int limit;
	/**
	 * 记录总数
	 */
	private Long total;
	/**
	 * 搜索条件
	 */
	private Dto searchParams;
	/**
	 * 搜索条件
	 */
	private Dto sortParams;

	/**
	 * 排序 条件
	 */
	Dto sorts;
	/**
	 * 返回列表
	 */
	private List<T> list;


	
	public PageVO(){
		this.page=1;
		this.limit=20;
	}

	public int getPage() {
		return page;
	}

	public int getLimit() {
		return limit;
	}

	public Long getTotal() {
		return total;
	}

	public Dto getSearchParams() {
		return searchParams;
	}

	public Dto getSortParams() {
		return sortParams;
	}

	public Dto getSorts() {
		return sorts;
	}

	public List<T> getList() {
		return list;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public void setSearchParams(Dto searchParams) {
		this.searchParams = searchParams;
	}

	public void setSortParams(Dto sortParams) {
		this.sortParams = sortParams;
	}

	public void setSorts(Dto sorts) {
		this.sorts = sorts;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}