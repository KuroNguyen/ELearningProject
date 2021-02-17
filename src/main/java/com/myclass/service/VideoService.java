package com.myclass.service;

import java.util.List;

import com.myclass.dto.VideoDto;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 13, 2021	
 */
public interface VideoService {
	boolean isExistTitleWithCourseId(String title, int courseId);
	List<VideoDto> getAll();
	VideoDto getById(int id);
	void insert(VideoDto dto);
	void edit(VideoDto dto);
	void delete(int id);
}
