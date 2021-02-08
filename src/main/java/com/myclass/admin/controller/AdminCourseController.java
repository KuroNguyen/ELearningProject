package com.myclass.admin.controller;
/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 4, 2021	
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/course")
public class AdminCourseController {

	@GetMapping("")
	public Object index() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
