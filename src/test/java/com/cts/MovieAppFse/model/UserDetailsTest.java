package com.cts.MovieAppFse.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserDetailsTest {
	
	@Test
	@DisplayName("Testing User model")
	public void testUser() {
		User user  = new User();
		assertThat(user).isNotNull();
	}
	
	
	@Test
	@DisplayName("Testing getter setter of User model")
	public void testGetSetUsers() {
		User user=new User();
		
		user.setLoginid(123);
		user.setFirstName("mohit");
		user.setLastName("chaudhari");
		user.setContactno("6319381313");
		user.setEmail("mc@gmail.com");
		user.setPassword("mc@1234");
		
		assertEquals(123,user.getLoginid());
		assertEquals("mohit",user.getFirstName());
		assertEquals("chaudhari",user.getLastName());
		assertEquals("mc@gmail.com", user.getEmail());
		assertEquals("mc@1234", user.getPassword());
		assertEquals("6319381313", user.getContactno());

	}
	
	@Test
	@DisplayName("Testing constructor of Ticket model")
	public void testConst() {
		User user=new User(123,"mohit","chaudhari","mc@gmail.com","mc@1234","6319381313");
		assertEquals(123,user.getLoginid());
		assertEquals("mohit",user.getFirstName());
		assertEquals("chaudhari",user.getLastName());
		assertEquals("mc@gmail.com", user.getEmail());
		assertEquals("mc@1234", user.getPassword());
		assertEquals("6319381313", user.getContactno());

	}
}
