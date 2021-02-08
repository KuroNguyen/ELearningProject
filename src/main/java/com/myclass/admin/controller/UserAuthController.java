package com.myclass.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.UserDto;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/auth/register")
public class UserAuthController {
	
		@Autowired
		private UserService userService;
		
		@PostMapping("")
		public Object post(@RequestBody UserDto userDto) {
			try {
				 userService.insert(userDto);
				 System.out.println("Vao !!!!");
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				
			}
		}
		

}
