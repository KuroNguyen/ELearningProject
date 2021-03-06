package com.myclass.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;

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
	List<VideoDto> getMenuVideoByCourseId(int id);

	boolean checkProperty(String orderBy);

	Page<Video> findAllPaging(String orderBy, int i, int pageSize, boolean b);
}
