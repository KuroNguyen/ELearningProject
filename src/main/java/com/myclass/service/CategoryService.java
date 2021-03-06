package com.myclass.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.myclass.dto.CategoryDto;


public interface CategoryService {
	
	void save(CategoryDto dto);
	List<CategoryDto> getAll();
	CategoryDto getById(int id);
	void edit(CategoryDto dto);
	void delete(int id);
	boolean checkExistById(int categoryId);
	MultiValueMap<String, String> getAllCategory();
	MultiValueMap<String, String> getCategoryById(int id);

}