package com.example.rest;

import com.example.rest.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;
	@Test
	void contextLoads() throws JsonProcessingException {

		/*var user = new UserRequest();
		user.setUserName("홍길동");
		user.setUserAge(20);
		user.setEmail("hong@gmail.com");
		user.setIsKorean(true);*/

//		var json = objectMapper.writeValueAsString(user);
		var json = "{\"user_name\":\"홍길동\",\"user_age\":20,\"email\":\"hong@gmail.com\",\"is_korean\":true}";
		System.out.println(json);

		var dto = objectMapper.readValue(json, UserRequest.class);
		System.out.println(dto);

	}

}
