package com.myclass.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.BuyCourseDto;
import com.myclass.dto.CourseDetailDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.MenuCourseDto;
import com.myclass.dto.UserCourseDto;
import com.myclass.entity.Course;

/**
 * Author: Nguyen Chanh Truc Created: Feb 9, 2021
 */
public interface CourseService {
	List<CourseDto> getAllWithCategory();

	List<CourseDto> getAllByCategoryId(int id);
	
	List<CourseDto> getAllByEmail(String email);

	void add(AddCourseDto entity);

	void edit(CourseDto entity);

	void deleteById(int id);

	CourseDto getCourseById(int id);

	CourseDetailDto getCourseDetailsById(int id);

	List<MenuCourseDto> getMenuCourseByCategoryId(int id);

	boolean checkExistByTitle(String title);

	boolean checkExistById(int id);

	CourseDto findCourseDtoById(int id);

	Page<Course> findAllPaging(String orderBy, int pageIndex, int pageSize, boolean descending);

	boolean checkProperty(String orderBy);

	String getImageById(int id);

	void editImageById(int id, String image);

	List<UserCourseDto> getAllUserCourse();

	boolean checkUserWithCourse(AddUserCourseDto dto);

	List<UserCourseDto> getAllCourseByUserId(int userId);

	List<UserCourseDto> getAllUserByCourseId(int courseId);

	void addCourse(AddUserCourseDto dto);

	void buyCourse(BuyCourseDto dto, UserDetails userDetails);
}