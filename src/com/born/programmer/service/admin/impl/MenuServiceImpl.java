package com.born.programmer.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.born.programmer.dao.admin.IMenuMapper;
import com.born.programmer.entity.admin.Menu;
import com.born.programmer.service.admin.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private IMenuMapper menuMapper;
	
	
	@Override
	public int addMenu(Menu menu) {
		return menuMapper.addMenu(menu);
	}

	/**
	 * 分页 模糊查询 获取指定页菜单列表
	 */
	@Override
	public List<Menu> getMenuList() {
		// TODO Auto-generated method stub
		return menuMapper.getMenuList();
	}


	@Override
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
