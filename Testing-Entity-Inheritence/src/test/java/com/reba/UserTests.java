package com.reba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.reba.entities.User;
import com.reba.exception.UserIncorrectPasswordException;
import com.reba.exception.UserNotFoundException;
import com.reba.repo.UserRepository;
import com.reba.service.UserServiceImpl;

@SpringBootTest
public class UserTests {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepo;
	
	@Test
	@DisplayName("Testing Login User")
	public void testLoginUser() {
		User user = new User(10,"Pedrosa","Broker","1234567","pd@pd.com","Goa");
		Optional<User> userOpt = Optional.of(user);
		Mockito.when(userRepo.findById(10)).thenReturn(userOpt);
		
		assertEquals(user, userService.loginUser(user));
	}
	
	@Test
	@DisplayName("Testing Logout User")
	public void testLogoutUser() {
		User user = new User(10,"Pedrosa","Broker","1234567","pd@pd.com","Goa");
		Optional<User> userOpt = Optional.of(user);
		Mockito.when(userRepo.findById(10)).thenReturn(userOpt);
		
		assertEquals(user, userService.logoutUser(user));
	}
	
	@Test
	@DisplayName("Testing UserNotFoundException")
	public void testUserException() {
		User user = new User(10,"Pedrosa","Broker","1234567","pd@pd.com","Goa");
		Optional<User> userOpt = Optional.of(user);
		Mockito.when(userRepo.findById(10)).thenReturn(userOpt);
		
		Exception exception = assertThrows(UserNotFoundException.class, () -> userService.loginUser(new User(11,"Pedrosa","Broker","1234567","pd@pd.com","Goa")));
		assertEquals("User Not Found", exception.getMessage());
	}
	
	@Test
	@DisplayName("Testing IncorrectPasswordException")
	public void testIncorrectPasswordException() {
		User user = new User(10,"Pedrosa","Broker","1234567","pd@pd.com","Goa");
		Optional<User> userOpt = Optional.of(user);
		Mockito.when(userRepo.findById(10)).thenReturn(userOpt);
		
		Exception exception = assertThrows(UserIncorrectPasswordException.class, () -> userService.loginUser(new User(10,"Pedros","Broker","1234567","pd@pd.com","Goa")));
		assertEquals("Incorrect Password", exception.getMessage());
	}
}
