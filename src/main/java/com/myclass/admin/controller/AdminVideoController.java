package com.myclass.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.dto.VideoDto;
import com.myclass.service.VideoService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 12, 2021	
 */
@RestController
@RequestMapping("api/admin/video")
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
	
	@GetMapping("")
	private Object get() {
		try {
			List<VideoDto> dtos = videoService.getAll();
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public Object getById(@PathVariable int id) {
		try {
			VideoDto video = videoService.getById(id);
			return new ResponseEntity<Object>(video, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody VideoDto videoDto) {
		try {
			if (id != videoDto.getId()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			if (videoService.getById(videoDto.getId()) == null) {
				return new ResponseEntity<Object>(NOT_EXIST,HttpStatus.BAD_REQUEST);
			}	
			videoService.edit(videoDto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			if (videoService.getById(id) == null) {
				return new ResponseEntity<Object>(NOT_EXIST,HttpStatus.BAD_REQUEST);
			}
			videoService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	

	
}
