package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.BuyCourseDto;
import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;

@RestController
@RequestMapping("api/course")
public class CourseController {
	private CourseService courseService;
	private CategoryService categoryService;

	@Value("${message.id}")
	private String idIsNotExist;
	
	@Value("${message.category}")
	private String categoryIsNotExist;

	@Value("${pattern}")
	private String pattern;

	@Value("${message.pattern}")
	private String patternIsNotValid;

	public CourseController(CourseService courseService, CategoryService categoryService) {
		this.courseService = courseService;
		this.categoryService = categoryService;
	}

	@GetMapping("category/{id}")
	public Object getMenu(@PathVariable int id) {
		try {
			// check xem category id có tồn tại hay không
			if (!categoryService.checkExistById(id))
				return new ResponseEntity<Object>(categoryIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về danh sách course theo category id gửi lên
//			return new ResponseEntity<Object>(courseService.getMenuCourseByCategoryId(id), HttpStatus.OK);
			return new ResponseEntity<Object>(courseService.getAllByCategoryId(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("")
	private Object getCourses() {
		try {
			List<CourseDto> dtos = courseService.getAllWithCategory();
			return new ResponseEntity<Object>(dtos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về course theo id đã gửi lên
//			return new ResponseEntity<Object>(courseService.findCourseDtoById(id), HttpStatus.OK);
			return new ResponseEntity<Object>(courseService.getCourseDetailsById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	

	@GetMapping("learning")
	public Object getLearning(@AuthenticationPrincipal UserDetails userDetails) {
		System.out.println(userDetails.getUsername());
		String email = userDetails.getUsername();
		try {
			List<CourseDto> dtos = courseService.getAllByEmail(email);
			return new ResponseEntity<Object>(dtos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("paging/{orderBy}/{pageIndex}/{pageSize}")
	public ResponseEntity<Page<Course>> paging(@PathVariable String orderBy, @PathVariable int pageIndex,
			@PathVariable int pageSize) {
		if (!orderBy.matches(pattern))
			return new ResponseEntity<Page<Course>>(HttpStatus.BAD_REQUEST);

		if (!courseService.checkProperty(orderBy))
			return new ResponseEntity<Page<Course>>(HttpStatus.BAD_REQUEST);

		Page<Course> courseList = courseService.findAllPaging(orderBy, pageIndex - 1, pageSize, false);
		if (courseList.getSize() == 0)
			return new ResponseEntity<Page<Course>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<Page<Course>>(courseList, HttpStatus.OK);
	}

	@GetMapping("paging/{orderBy}/{pageIndex}/{pageSize}/descending")
	public ResponseEntity<Page<Course>> pagingDescending(@PathVariable String orderBy, @PathVariable int pageIndex,
			@PathVariable int pageSize) {
		if (!orderBy.matches(pattern))
			return new ResponseEntity<Page<Course>>(HttpStatus.BAD_REQUEST);

		if (!courseService.checkProperty(orderBy))
			return new ResponseEntity<Page<Course>>(HttpStatus.BAD_REQUEST);
		
		Page<Course> courseList = courseService.findAllPaging(orderBy, pageIndex - 1, pageSize, true);

		if (courseList.getSize() == 0)
			return new ResponseEntity<Page<Course>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<Page<Course>>(courseList, HttpStatus.OK);
	}
	
	/**
	 * Temporary api without payment implementation
	 * @param buyCourseDto
	 * @return
	 */
	@PostMapping("buy")
	public Object buy(@RequestBody BuyCourseDto buyCourseDto, @AuthenticationPrincipal UserDetails userDetails) {
		try {
			System.out.println(userDetails.getUsername());
			courseService.buyCourse(buyCourseDto, userDetails);
			return new ResponseEntity<Object>(HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST); 
	}
}