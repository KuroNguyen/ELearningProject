package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.UserCourseDto;

/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 10, 2021	
 */
@Service
public interface UserCourseService {
	List<UserCourseDto> getAll();
	
	void insert(UserCourseDto userCourseDto);
}
