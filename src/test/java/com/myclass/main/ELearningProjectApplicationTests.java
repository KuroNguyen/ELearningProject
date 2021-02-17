package com.myclass.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;
import com.myclass.service.VideoService;


@SpringBootTest
class ELearningProjectApplicationTests {
	
	// Inject VideoService
	@Autowired
	private VideoService videoService;

	@Test
	void contextLoads() {
		System.out.println("TEST");
		String testString = "admin@gmail.com";
		String result = testString.split("@")[0];
		assertEquals("admin", result);
	}

}
