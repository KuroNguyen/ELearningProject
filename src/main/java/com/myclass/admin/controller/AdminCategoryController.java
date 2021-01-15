package com.myclass.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.CategoryDto;
import com.myclass.service.CategoryService;

@RestController
@RequestMapping("api/admin/category")
public class AdminCategoryController {
	
	private CategoryService categoryService;

	public AdminCategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping("")
	public Object post(@RequestBody CategoryDto dto) {
		try {
			categoryService.save(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
