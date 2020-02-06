package com.cos.blog.repository;

import com.cos.blog.model.user.dto.ReqJoinDto;

public interface UserRepository {
	int save(ReqJoinDto dto);
	int findByUsername(String username);
}
