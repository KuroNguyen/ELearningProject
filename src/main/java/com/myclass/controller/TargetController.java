package com.myclass.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.service.CourseService;
import com.myclass.service.TargetService;

@RestController
@RequestMapping("/api/target")
public class TargetController {
	private CourseService courseService;
	private TargetService targetService;
	
	@Value("${message.id}")
	private String idIsNotExist;

	@Value("${message.course}")
	private String courseIsNotExist;

	public TargetController(CourseService courseService, TargetService targetService) {
		this.courseService = courseService;
		this.targetService = targetService;
	}

	@GetMapping("course/{id}")
	public Object getMenu(@PathVariable int id) {
		try {
			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(id))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về danh sách target theo course id gửi lên
			return new ResponseEntity<Object>(targetService.getMenuTargetByCourseId(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public Object getTargetByid(@PathVariable int id) {
		try {
			// check xem target id có tồn tại hay không
			if (!targetService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về target theo id gửi lên
			return new ResponseEntity<Object>(targetService.getTargetById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("")
	public Object get() {
		try {
			// trả về danh sách target
			return new ResponseEntity<Object>(targetService.getAllWithCourse(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
