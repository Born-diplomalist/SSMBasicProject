package com.born.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.born.programmer.entity.admin.Menu;
import com.born.programmer.service.admin.IMenuService;

@RequestMapping("/admin/menu")
@Controller
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	
	/**
	 * 菜单管理列表页
	  * @param model
	  * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("menu/list");
		return model;
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getMenuList() {
		Map<String,Object> map = new HashMap<String,Object>();
		return map;
		
	}
	
	
	/**
	 * 菜单的添加
	  * @param menu
	  * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(Menu menu){
		Map<String,String> map=new HashMap<>();
		//参数校验
		if(menu == null) {
			map.put("type", "error");
			map.put("msg","请填写正确的菜单信息！");
		}
		//import org.apache.commons.lang.StringUtils;
		if(StringUtils.isEmpty(menu.getName())) {
			map.put("type", "error");
			map.put("msg","请填写菜单名！");
		}
		if(StringUtils.isEmpty(menu.getIcon())) {
			map.put("type", "error");
			map.put("msg","请填写菜单图标！");
		}
		
		//未赋父id的菜单对象给予默认父id -1L
		if(menu.getParentId()==null) {
			menu.setParentId(-1l);
			System.out.println(menu);
		}
		if(menuService.addMenu(menu)<=0) {
			map.put("type", "error");
			map.put("msg","添加失败！");
		}
		map.put("type", "success");
		map.put("msg","添加成功");
		

		return map;
	}
	
	
}
