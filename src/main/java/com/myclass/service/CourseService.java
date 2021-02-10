package com.myclass.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.MenuCourseDto;
import com.myclass.entity.Course;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 9, 2021	
 */
public interface CourseService {
	List<CourseDto> getAllWithCategory();

	void add(AddCourseDto entity);

	void edit(CourseDto entity);

	void deleteById(int id);

	CourseDto getCourseById(int id);

	List<MenuCourseDto> getMenuCourseByCategoryId(int id);

	boolean checkExistByTitle(String title);

	boolean checkExistById(int id);

	CourseDto findCourseDtoById(int id);

	Page<Course> findAllPaging(String orderBy, int pageIndex, int pageSize, boolean descending);

	boolean checkProperty(String orderBy);

	String getImageById(int id);

	void editImageById(int id, String image);
}
