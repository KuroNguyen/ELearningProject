package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.UserCourseDto;
import com.myclass.entity.UserCourseKey;
import com.myclass.entity.UserCourses;
import com.myclass.repository.BaseRepository;
@Repository
public interface UserCourseService extends BaseRepository<UserCourses, UserCourseKey> {

	List<UserCourseDto> getAllUserCourse();

	boolean checkUserWithCourse(AddUserCourseDto dto);

	List<UserCourseDto> getAllCourseByUserId(int userId);

	List<UserCourseDto> getAllUserByCourseId(int courseId);

	void addCourse(AddUserCourseDto dto);

}
