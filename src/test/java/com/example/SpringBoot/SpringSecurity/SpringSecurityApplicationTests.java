package com.example.SpringBoot.SpringSecurity;

import com.example.SpringBoot.SpringSecurity.Entities.User;
import com.example.SpringBoot.SpringSecurity.Services.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	private JWTService jwtService;

	@Test
	void contextLoads() {

		User user = new User(4L, "nisarg@gmail.com", "1234");

		String token = jwtService.generateAccessToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);

	}

}
