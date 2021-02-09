package com.myclass.repository;

import java.util.List;

import com.myclass.entity.UserCourses;
import com.myclass.service.impl.UserServiceImpl;

public interface UserCourseRepository {

	void save(UserCourses userCourse);

	List<UserCourses> findAll();

}
