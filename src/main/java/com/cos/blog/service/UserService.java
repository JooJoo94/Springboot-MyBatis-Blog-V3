package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RespCode;
import com.cos.blog.model.user.dto.ReqJoinDto;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// result = 0 비정상, 1 정상, -1 DB 오류, -2 아이디 중복
	@Transactional
	public int 회원가입(ReqJoinDto dto) {
		try {
			int result = userRepository.findByUsername(dto.getUsername());
			
			if(result == 1) {
				return RespCode.아이디중복;
			}else {
				return userRepository.save(dto);
			}
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
}
