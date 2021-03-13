package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 9, 2021	
 */
@Repository
public interface CourseRepository extends BaseRepository<Course, Integer>{
	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, ct.id, ct.title, c.lastUpdate) FROM Course c LEFT JOIN Category ct ON c.categoryId = ct.id")
	public List<CourseDto> getAllWithCategory();

	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, ct.id, ct.title, c.lastUpdate) FROM User u RIGHT JOIN UserCourses uc ON u.id = uc.user.id RIGHT JOIN Course c ON c.id = uc.course.id RIGHT JOIN Category ct ON ct.id = c.categoryId WHERE u.email = :email")
	public List<CourseDto> getAllWithCategoryByEmail(@Param("email") String email);
	
	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, ct.id, ct.title, c.lastUpdate) FROM Course c LEFT JOIN Category ct ON c.categoryId = ct.id WHERE c.categoryId = :id")
	public List<CourseDto> getAllByCategoryId(@Param("id") int id);

	public Course findByTitle(String title);

	public List<Course> findAllByCategoryId(int id);

	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, ct.id, ct.title, c.lastUpdate) FROM Course c LEFT JOIN Category ct ON c.categoryId = ct.id WHERE c.id = :id")
	public CourseDto getCourseDtoWithCategoryById(@Param("id") int id);
	
}
