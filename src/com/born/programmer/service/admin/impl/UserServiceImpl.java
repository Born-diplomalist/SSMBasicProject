package com.born.programmer.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.born.programmer.dao.admin.IUserMapper;
import com.born.programmer.entity.admin.User;
import com.born.programmer.service.admin.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserMapper userDao;
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
