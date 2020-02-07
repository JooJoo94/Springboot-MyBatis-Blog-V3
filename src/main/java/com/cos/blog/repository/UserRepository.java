package com.cos.blog.repository;

import com.cos.blog.model.user.User;
import com.cos.blog.model.user.dto.ReqJoinDto;
import com.cos.blog.model.user.dto.ReqLoginDto;

public interface UserRepository {
	int save(ReqJoinDto dto);
	int findByUsername(String username);
	User findByUsernameAndPassword(ReqLoginDto dto);
}
