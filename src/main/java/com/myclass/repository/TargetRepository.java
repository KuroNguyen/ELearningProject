package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 7, 2021	
 */
public interface TargetRepository extends BaseRepository<Target, Integer>{
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id")
	public List<TargetDto> findAllDto();
	public Target findByTitle(String title);
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id WHERE t.id = :id")
	public List<TargetDto> findAllByCourseId(int id);
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id WHERE c.id = :id")
	public List<TargetDto> getMenuTargetByCourseId(@Param("id") int id);
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id")
	public List<TargetDto> getAllWithCourse();

}
