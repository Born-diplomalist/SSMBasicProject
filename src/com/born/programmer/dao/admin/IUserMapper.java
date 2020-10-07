package com.born.programmer.dao.admin;

import org.springframework.stereotype.Repository;

import com.born.programmer.entity.admin.User;

@Repository
public interface IUserMapper {

	User findByUsername(String username);
}
