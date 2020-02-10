package com.cos.blog.model.user;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// MaBatis에서 ResultType으로 담을 때 생성자 혹은 Setter중 무엇이 호출되는지 확인 후 Lombok 변경
@Data
@NoArgsConstructor
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private Timestamp createDate;
	
	@Builder
	public User(String username, String password, String email, String profile) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
	}
}
