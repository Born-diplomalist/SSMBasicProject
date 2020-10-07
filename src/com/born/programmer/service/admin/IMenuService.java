package com.born.programmer.service.admin;

import java.util.List;

import com.born.programmer.entity.admin.Menu;

public interface IMenuService {

	
	int addMenu(Menu menu);
	
	List<Menu> getMenuList();
	
	int getTotalNum();
	
	
}
