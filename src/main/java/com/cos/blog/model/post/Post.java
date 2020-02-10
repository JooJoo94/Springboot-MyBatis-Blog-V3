package com.cos.blog.model.post;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Post {
	private int id;
	private String title;
	private String content;
	private int userId;
	private Timestamp createDate;
	
	@Builder
	public Post(String title, String content, int userId, Timestamp createDate) {
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.createDate = createDate;
	}
	
}
