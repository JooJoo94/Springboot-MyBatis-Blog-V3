package com.cos.blog.config;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cos.blog.model.RespCM;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
				.antMatchers(
						"/user/profile/**", 
						"/post/write/**", 
						"/post/detail/**", 
						"/post/update/**", 
						"/post/delete/**").authenticated()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')  or hasRole('ROLE_MANAGER')")
				.anyRequest().permitAll()
		.and()
				.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/loginProc") // post만 낚아챔
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						
						System.out.println("성공함~~~~~~~~~~~~~~~~~~~");
						PrintWriter out = response.getWriter();
						
						ObjectMapper mapper = new ObjectMapper();
						  
						// string 으로 저장
						String jsonString = mapper.writeValueAsString(new RespCM(200,"ok"));
						System.out.println("jsonString : "+jsonString);
						out.print(jsonString);
						out.flush();
						
						
					}
				}); // defaultSuccessUrl을 사용할 수 있음.		
	}
	
	@Autowired
	private UserDetailsService userDeService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDeService).passwordEncoder(encode());
	}
}
