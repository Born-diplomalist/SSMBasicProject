package com.born.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Menu {
	
	private Long id;
	/**
	 * 父菜单id
	 */
	private Long parentId;
	/**
	 * 父类id,用来匹配easyui的父类id
	 */
	private Long _parentId;
	/**
	 * 点击后跳转的url
	 */
	private String url;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 图标
	 */
	private String icon;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", parentId=" + parentId + ", _parentId=" + _parentId + ", url=" + url + ", name="
				+ name + ", icon=" + icon + "]";
	}
	
	

	
	
	
}
