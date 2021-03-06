package com.myclass.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.myclass.dto.AddVideoDto;
import com.myclass.dto.EditVideoDto;
import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 13, 2021	
 */
@Service
public class VideoServiceImpl implements VideoService {

	// Inject VideoRepository
	@Autowired
	private VideoRepository videoRepository;
	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}
	
	@Override
	public boolean isExistTitleWithCourseId(String title, int courseId) {
		// Get video title from database
		List<Video> videos = videoRepository.findAllByTitle(title);
		// Return true if any video have the same courseId
		return videos.stream()
			.anyMatch(video->video.getCourseId() == courseId);
	}

	@Override
	public List<VideoDto> getAll() {
		// Get VideoDto list
		return videoRepository.findAllJoin();
	}

	@Override
	public VideoDto getById(int id) {
		// Get entity from database
		Video entity = videoRepository.findById(id).get();
		// Convert from entity to dto
		VideoDto dto = new VideoDto();
		dto.setId(id);
		dto.setTitle(entity.getTitle());
		dto.setUrl(entity.getUrl());
		dto.setTimeCount(entity.getTimeCount());
		dto.setCourseId(entity.getCourseId());
		return dto;
	}

	@Override
	public void insert(VideoDto dto) {
		// Convert dto to entity
		Video entity = new Video();
		entity.setId(0);
		entity.setTitle(dto.getTitle());
		entity.setUrl(dto.getUrl());
		entity.setTimeCount(dto.getTimeCount());
		entity.setCourseId(dto.getCourseId());
		// Save entity to database
		videoRepository.save(entity);
	}

	@Override
	public void edit(VideoDto dto) {
		// Get entity from database
		Video entity = videoRepository.findById(dto.getId()).get();
		entity.setTitle(dto.getTitle());
		entity.setUrl(dto.getUrl());
		entity.setTimeCount(dto.getTimeCount());
		entity.setCourseId(dto.getCourseId());
		// Save entity to database
		videoRepository.save(entity);
	}

	@Override
	public void delete(int id) {
		videoRepository.deleteById(id);
	}

	@Override
	public List<VideoDto> getAllWithCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(AddVideoDto entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EditVideoDto getVideoById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(EditVideoDto entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkExistByTitle(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<VideoDto> getMenuVideoByCourseId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkProperty(String orderBy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<Video> findAllPaging(String orderBy, int i, int pageSize, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
}