package com.myclass.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Jan 29, 2021	
 */
@Service
public class TargetServiceImpl implements TargetService{
	
	// Inject target repository
	private TargetRepository targetRepository;
	public TargetServiceImpl(TargetRepository targetRepository) {
		this.targetRepository = targetRepository;
	}

	@Override
	public void save(TargetDto dto) {
		// Convert from dto to entity
		Target entity = Target.builder()
				.id(dto.getId())
				.courseId(dto.getCourseId())
				.title(dto.getTitle())
				.build();
		// Save entity to database
		targetRepository.save(entity);
	}

	@Override
	public List<TargetDto> getAll() {
		// Get all entities
//		List<Target> entities = targetRepository.findAll();
//		for (Target target : entities) {
//			System.out.println(target.toString());
//		}
//		// Convert from entities to dtos and return
//		return entities.stream()
//			.map(entity -> convertEntityToDto(entity))
//			.collect(Collectors.toList());
		return targetRepository.findAllDto();
	}

	@Override
	public TargetDto getById(int id) {
		// Get entity by id
		Target entity = targetRepository.findById(id).get();
		// Convert entity to dto and return
		return convertEntityToDto(entity);
	}

	@Override
	public void edit(TargetDto dto) {
		// Get entity with dto.id from database
		Target entity = targetRepository.findById(dto.getId()).get();
		// Set entity properties by dto properties respectively
		entity.setCourseId(dto.getCourseId());
		entity.setTitle(dto.getTitle());
		// Save entity to database
		targetRepository.save(entity);
	}

	@Override
	public void delete(int id) {
		// Delete entity by id
		targetRepository.deleteById(id);
	}

	private TargetDto convertEntityToDto(Target entity) {
		TargetDto dto = TargetDto.builder()
				.id(entity.getId())
				.courseId(entity.getCourseId())
				.title(entity.getTitle())
				.build();
		return dto;
	}

	@Override
	public List<TargetDto> getAllByCourseId(int id) {
		return targetRepository.findAllByCourseId(id);
	}
}
