package com.myclass.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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
		return entities.stream() // Convert to stream
				.map((entity) -> convertEntityToDto(entity)) // convert from entity to dto
				.collect(Collectors.toList()); // collect dtos to list
	}

	@Override
	public CategoryDto getById(int id) {
		// Get category from database
		Category entity = categoryRepository.findById(id).get();
		if (entity != null) {
			return convertEntityToDto(entity);	
		}
		return null;
	}

	@Override
	public void edit(CategoryDto dto) {
		// Get category from database
		Category entity = categoryRepository.findById(dto.getId()).get();
		if (entity == null) return;
		// Set data from dto to entity
		entity.setTitle(dto.getTitle());
		entity.setIcon(dto.getIcon());
		// Update category
		categoryRepository.save(entity);
	}

	@Override
	public void delete(int id) {
		// Delete category
		categoryRepository.deleteById(id);
	}

	CategoryDto convertEntityToDto(Category category) {
		CategoryDto dto = new CategoryDto();
		dto.setId(category.getId());
		dto.setTitle(category.getTitle());
		dto.setIcon(category.getIcon());
		return dto;
	}

	@Override
	public boolean checkExistById(int id) {
		// kiểm tra id có tồn tại dưới database chưa
		return categoryRepository.findById(id).isPresent();
	}

	@Override
	public MultiValueMap<String, String> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiValueMap<String, String> getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}