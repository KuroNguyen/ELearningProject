package com.myclass.main;
/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 10, 2021	
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myclass.dto.UserCourseDto;
import com.myclass.service.UserCourseService;

@SpringBootTest
public class UserCourseServiceTest {
	
	@Autowired
	private UserCourseService userCourseService;
	
	@Test
	void contextLoads() {
		List<UserCourseDto> dtos = userCourseService.getAll();
		
		System.out.println(dtos);
	}
}
