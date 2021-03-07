package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 13, 2021	
 */
public interface VideoRepository extends BaseRepository<Video, Integer>{
	
	
	@Query("SELECT new com.myclass.dto.VideoDto(v.id, v.title, v.url, v.timeCount, v.courseId, c.title) FROM Video v LEFT JOIN Course c ON v.courseId = c.id")
	List<VideoDto> findAllJoin();
	List<Video> findAllByTitle(String title);
	public Video findByTitle(String title);
	
	@Query("SELECT new com.myclass.dto.VideoDto(v.id, v.title, v.url, v.timeCount, v.courseId, c.title) FROM Video v LEFT JOIN Course c ON v.courseId = c.id ")
	public List<VideoDto> getMenuVideoByCourseId(int id);
}
