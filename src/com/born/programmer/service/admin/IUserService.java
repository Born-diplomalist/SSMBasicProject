package com.born.programmer.service.admin;

import org.springframework.stereotype.Service;

import com.born.programmer.entity.admin.User;

@Service
public interface IUserService {

	User findByUsername(String username);
	
}
