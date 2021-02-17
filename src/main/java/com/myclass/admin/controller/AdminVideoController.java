package com.myclass.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.VideoDto;
import com.myclass.service.VideoService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 12, 2021	
 */
@RestController
@RequestMapping()
public class AdminVideoController {
	// Already exist message
	private String TITLE_ALREADY_EXIST;
	// Not exist message
	private String NOT_EXIST;
	// CourseId not exist
	private String COURSE_ID_NOT_EXIST;
	
	// Inject VideoService
	private VideoService videoService;
	public AdminVideoController(VideoService videoService) {
		this.videoService = videoService;
	}

	@PostMapping("")
	public Object post(@RequestBody VideoDto dto) {
		// Check duplicate title with course
		if (videoService.isExistTitleWithCourseId(dto.getTitle(), dto.getCourseId())) {
			return new ResponseEntity<Object>(TITLE_ALREADY_EXIST,HttpStatus.BAD_REQUEST);
		}
		// Insert video
		try {
			videoService.insert(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	

	
}
