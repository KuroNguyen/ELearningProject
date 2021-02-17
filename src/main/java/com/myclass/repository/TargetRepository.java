package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 7, 2021	
 */
public interface TargetRepository extends JpaRepository<Target, Integer>{
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, t.courseId, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id")
	public List<TargetDto> findAllDto();
	
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, t.courseId, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id WHERE t.id = :id")
	public List<TargetDto> findAllByCourseId(int id);
}
