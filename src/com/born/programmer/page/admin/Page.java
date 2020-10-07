package com.born.programmer.page.admin;

import org.springframework.stereotype.Component;

/**
 * 分页基本信息
  * @author 15188
  *
 */
@Component
public class Page {

	/**
	 * 当前页码
	 */
	private int page=1;
	/**
	 * 偏移量 即起始下标
	 */
	private int offset;
	/**
	 * 步长
	 */
	private int pageSize;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffset() {
		return this.offset = (page - 1) * pageSize;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
}
