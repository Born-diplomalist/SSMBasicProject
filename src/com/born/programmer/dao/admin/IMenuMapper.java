package com.born.programmer.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.programmer.entity.admin.Menu;

@Repository
public interface IMenuMapper {

	int addMenu(Menu menu);
	
	/**
	 * 获取菜单
	  * @return
	 */
	List<Menu> getMenuList();
	
	int getTotalNum();
}
