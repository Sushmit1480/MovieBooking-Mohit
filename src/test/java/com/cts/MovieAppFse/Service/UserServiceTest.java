package com.cts.MovieAppFse.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.MovieAppFse.model.User;
import com.cts.MovieAppFse.model.UserLogin;
import com.cts.MovieAppFse.repository.UserRepository;
import com.cts.MovieAppFse.service.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	@DisplayName("Testing user service class loading")
	public void testServiceClass() {
		assertThat(userService).isNotNull();
	}
	
	
	@Test
	@DisplayName("Testing Login user")
	public void testLoginUser() {
		User user=new User(123,"Mohit","Chaudhari","mc@gmail.com","mca@1234","63436314");
		UserLogin userLogin=new UserLogin("mc@gmail.com","mca@1234");
		
		when(userRepository.findByEmail(userLogin.getEmail())).thenReturn(user);
		
		assertEquals(user, userService.loginUser(userLogin));
		assertEquals(null, userService.loginUser(new UserLogin("mk@gmail.com","Mk@123")));

		
	}
	
	@Test
	@DisplayName("Testing forgot password")
	public void testForgotPassword() {
		UserLogin user = new UserLogin("srk@gmail.com", "abc@123");
		User user1 = new User(102,"srk@gmail.com","Sharukh","Khan","srk@123","1234567890");
		when(userRepository.findByEmail(user.getEmail())).thenReturn(user1);
//		doNothing().when(userRepository.save(user1));
		assertEquals(true,userService.forgetPassword(user));
		assertEquals(false,userService.forgetPassword(new UserLogin("sr@gmail.com", "srk@123")));
	}
	
	
}
