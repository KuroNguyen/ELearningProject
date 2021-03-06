package com.myclass.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Video;
import com.myclass.service.CourseService;
import com.myclass.service.VideoService;

@RestController
@RequestMapping("/api/video")
public class VideoController {
	private CourseService courseService;
	private VideoService videoService;
	
	@Value("${message.id}")
	private String idIsNotExist;

	@Value("${message.course}")
	private String courseIsNotExist;
	
	@Value("${pattern}")
	private String pattern;

	@Value("${message.pattern}")
	private String patternIsNotValid;

	public VideoController(CourseService courseService, VideoService videoService) {
		this.courseService = courseService;
		this.videoService = videoService;
	}

	@GetMapping("course/{id}")
	public Object getMenu(@PathVariable int id) {
		try {
			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(id))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về danh sách video theo course id gửi lên
			return new ResponseEntity<Object>(videoService.getMenuVideoByCourseId(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public Object getVideoByid(@PathVariable int id) {
		try {
			// check xem video id có tồn tại hay không
			if (!videoService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về video theo id gửi lên
			return new ResponseEntity<Object>(videoService.getVideoById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("")
	public Object get() {
		try {
			// trả về danh sách video
			return new ResponseEntity<Object>(videoService.getAllWithCourse(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("paging/{orderBy}/{pageIndex}/{pageSize}")
	public ResponseEntity<Page<Video>> paging(@PathVariable String orderBy, @PathVariable int pageIndex,
			@PathVariable int pageSize) {
		if (!orderBy.matches(pattern))
			return new ResponseEntity<Page<Video>>(HttpStatus.BAD_REQUEST);

		if (!videoService.checkProperty(orderBy))
			return new ResponseEntity<Page<Video>>(HttpStatus.BAD_REQUEST);

		Page<Video> videoList = videoService.findAllPaging(orderBy, pageIndex - 1, pageSize, false);
		if (videoList.getSize() == 0)
			return new ResponseEntity<Page<Video>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<Page<Video>>(videoList, HttpStatus.OK);
	}

	@GetMapping("paging/{orderBy}/{pageIndex}/{pageSize}/descending")
	public ResponseEntity<Page<Video>> pagingDescending(@PathVariable String orderBy, @PathVariable int pageIndex,
			@PathVariable int pageSize) {
		if (!orderBy.matches(pattern))
			return new ResponseEntity<Page<Video>>(HttpStatus.BAD_REQUEST);

		if (!videoService.checkProperty(orderBy))
			return new ResponseEntity<Page<Video>>(HttpStatus.BAD_REQUEST);

		Page<Video> videoList = videoService.findAllPaging(orderBy, pageIndex - 1, pageSize, true);
		if (videoList.getSize() == 0)
			return new ResponseEntity<Page<Video>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<Page<Video>>(videoList, HttpStatus.OK);
	}
}