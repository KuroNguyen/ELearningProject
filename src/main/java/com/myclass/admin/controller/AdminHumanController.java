package com.myclass.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.UserDto;
import com.myclass.service.UserService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 2, 2021	
 */
@RestController
@RequestMapping("api/admin/human")
public class AdminHumanController {
	
	private UserService userService;
	public AdminHumanController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("")
	public Object get() {
		try {
			List<UserDto> users = userService.getAll();
			return new ResponseEntity<Object>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{id}")
	public Object getById(@PathVariable int id) {
		try {
			UserDto user = userService.getById(id);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("")
	public Object post(@RequestBody UserDto userDto) {
		try {
			userService.insert(userDto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody UserDto userDto) {
		try {
			System.out.println("Test: " + userDto.toString());
			if (id != userDto.getId()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			userService.edit(userDto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			userService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
