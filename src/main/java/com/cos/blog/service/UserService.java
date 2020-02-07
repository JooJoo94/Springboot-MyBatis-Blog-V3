package com.cos.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.controller.UserController;
import com.cos.blog.model.ReturnCode;
import com.cos.blog.model.user.User;
import com.cos.blog.model.user.dto.ReqJoinDto;
import com.cos.blog.model.user.dto.ReqLoginDto;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	// result = 0 비정상, 1 정상, -1 DB 오류, -2 아이디 중복
	@Transactional
	public int 회원가입(ReqJoinDto dto) {
		try {
			int result = userRepository.findByUsername(dto.getUsername());
			
			if(result == 1) {
				return ReturnCode.아이디중복;
			}else {
				return userRepository.save(dto);
			}
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public User 로그인(ReqLoginDto dto) {
		return userRepository.findByUsernameAndPassword(dto);
	}
	
}
