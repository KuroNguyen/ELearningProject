package com.myclass.service;

import java.util.List;

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
}
