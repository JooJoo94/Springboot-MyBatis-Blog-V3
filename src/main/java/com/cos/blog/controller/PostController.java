package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.model.user.User;

// 시큐리티 구현 완료

@Controller
public class PostController {

	@Autowired
	private HttpSession session;
	
	@GetMapping({"", "/", "/post"})
	public String posts() {
		return "/post/list";
	}
	
	@GetMapping("/post/{id}")
	public String post() {
		return "/post/detail";
	}
	
	// 인증 체크
	@GetMapping("/post/write")
	public String write() {
		return "/post/write";
	}
	
	// 인증 체크, 동일인 체크
	@GetMapping("/post/update/{postId}")
	public String update(@PathVariable int postId, @RequestParam int userId) {
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal.getId() != userId) {
			return "/user/login";
		}
		
		// postId 로 select 해서 post 가져오기 필요 - Model에 담기 필요
		
			
		return "/post/update";
	}
}
