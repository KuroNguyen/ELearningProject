package com.myclass.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserCourseDto;
import com.myclass.entity.UserCourseKey;
import com.myclass.entity.UserCourses;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserCourseService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 10, 2021	
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserCourseServiceImpl implements UserCourseService {

	@Autowired
	private UserCourseRepository userCourseRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserCourseDto> getAll() {
		return userCourseRepository.findAll().stream()
				.map(userCourse -> convertUserCourseToUserCourseDto(userCourse))
				.collect(Collectors.toList());
	}
	
	private UserCourseDto convertUserCourseToUserCourseDto(UserCourses entity) {
		UserCourseDto dto = new UserCourseDto();
		dto.setEmail(entity.getUser().getEmail());
		dto.setCourseTitle(entity.getCourse().getTitle());
		return dto;
	}

	@Override
	public void insert(UserCourseDto userCourseDto) {
		// Convert UserCourseDto to userCourse
		UserCourses entity = new UserCourses();
		UserCourseKey pk = new UserCourseKey();
		entity.setId(pk);
		entity.setUser(userRepository.findByEmail(userCourseDto.getEmail()));
		entity.setCourse(courseRepository.findByTitle(userCourseDto.getCourseTitle()));
		entity.setRoleId(1);
		
		userCourseRepository.save(entity);
	}

}
