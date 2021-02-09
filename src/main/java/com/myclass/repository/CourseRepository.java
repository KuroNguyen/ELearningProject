package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course, Integer> {

	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, c.category, c.lastUpdate) FROM Course c LEFT JOIN Category ct ON c.categoryId = ct.id")
	public List<CourseDto> getAllWithCategory();

	public Course findByTitle(String title);

	public List<Course> findAllByCategoryId(int id);

	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, c.category, c.lastUpdate) FROM Course c LEFT JOIN Category ct ON c.categoryId = ct.id WHERE c.id = :id")
	public CourseDto getCourseDtoWithCategoryById(@Param("id") int id);

	public void saveAll(Course course);

	

	
}
