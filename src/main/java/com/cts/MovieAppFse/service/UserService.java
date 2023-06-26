package com.cts.MovieAppFse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.MovieAppFse.model.User;
import com.cts.MovieAppFse.model.UserLogin;
import com.cts.MovieAppFse.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User loginUser(UserLogin userLogin) {
		User user=userRepository.findByEmail(userLogin.getEmail());
		if(user!=null && user.getPassword().equals(userLogin.getPassword())) {
			return user;
		}else {
			return null;
		}
	}
	
	public boolean forgetPassword(UserLogin userlogin) {
		
		
		User user=userRepository.findByEmail(userlogin.getEmail());
		if(user!=null) {
			user.setPassword(userlogin.getPassword());
			userRepository.save(user);
			return true;
		}
		
		//user does not exists
		return false;
	}
}
