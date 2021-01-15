package com.myclass.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myclass.dto.CategoryDto;
import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void save(CategoryDto dto) {
		Category entity = new Category();
		entity.setTitle(dto.getTitle());
		entity.setIcon(dto.getIcon());
		
		categoryRepository.save(entity);
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> entities = categoryRepository.findAll();
		return entities.stream().map((entity) -> convertEntityToDto(entity)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(CategoryDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	CategoryDto convertEntityToDto(Category category) {
		CategoryDto dto = new CategoryDto();
		dto.setId(category.getId());
		dto.setTitle(category.getTitle());
		dto.setIcon(category.getIcon());
		return dto;
	}
}
