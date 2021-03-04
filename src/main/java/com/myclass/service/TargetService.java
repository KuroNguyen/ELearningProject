package com.myclass.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.myclass.dto.TargetDto;

/**
 * Author: Nguyen Chanh Truc
 * Created: Jan 29, 2021	
 */
public interface TargetService {
	void save(TargetDto dto);
	List<TargetDto> getAll();
	List<TargetDto> getAllByCourseId(int id);
	TargetDto getById(int id);
	Boolean isTargetIdExist(int id);
	void edit(TargetDto dto);
	void delete(int id);
	MultiValueMap<String, String> getAllWithCourse();
	MultiValueMap<String, String> getMenuTargetByCourseId(int id);
	boolean checkExistById(int id);
	MultiValueMap<String, String> getTargetById(int id);
}
