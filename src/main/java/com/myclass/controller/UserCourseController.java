package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.UserCourseDto;
import com.myclass.service.UserCourseService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 10, 2021	
 */
@RestController
@RequestMapping("api/usercourse")
public class UserCourseController {
	
	@Autowired
	private UserCourseService userCourseService;
	
	@GetMapping("")
	public Object get() {
		try {
			List<UserCourseDto> dtos = userCourseService.getAll();
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("")
	public Object post(@RequestBody UserCourseDto userCourseDto) {
		try {
			userCourseService.insert(userCourseDto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
