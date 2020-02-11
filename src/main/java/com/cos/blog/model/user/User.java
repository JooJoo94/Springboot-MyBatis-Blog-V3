package com.cos.blog.model.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// MaBatis에서 ResultType으로 담을 때 생성자 혹은 Setter중 무엇이 호출되는지 확인 후 Lombok 변경
@Data
@NoArgsConstructor
public class User implements UserDetails{
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private String role; // USER, MANAGER, ADMIN
	private Timestamp createDate;
	
	@Builder
	public User(String username, String password, String email, String profile, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.role = role;
	}
	
	public String getPassword() {
		System.out.println("getPassword() : "+password);
		return password;
	}
	
	public String getUsername() {
		System.out.println("getUsername()");
		return username;
	}
	
	// username과 password의 getter도 만들어져야 하는데
	// 우리는 필드명을 username과 password로 만들었고 Lombok도 있어서
	// 안만들어지는 것이다.

	// 여러개의 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new SimpleGrantedAuthority("ROLE_"+role));
		return collectors;
	}

	// 계정이 만료되었는지 체크하여 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 체크하여 리턴한다 (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되었는지 체크하여 리턴한다 (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 해당 계정이 활성화 되어있는지 체크하여 리턴한다 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}


	
	
}
