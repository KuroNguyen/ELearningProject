package com.myclass.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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

import com.myclass.dto.AddUserCourseDto;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/api/admin/userCourse")
public class AdminUserCourseController {
	private UserCourseService userCourseService;
	private UserService userService;
	private CourseService courseService;

	@Value("${message.user}")
	private String userIsNotExist;

	@Value("${message.course}")
	private String courseIsNotExist;

	public AdminUserCourseController(UserCourseService userCourseService, UserService userService,
			CourseService courseService) {
		this.userCourseService = userCourseService;
		this.userService = userService;
		this.courseService = courseService;
	}

	@GetMapping("")
	public Object get() {
		try {
			// trả về
			return new ResponseEntity<Object>(userCourseService.getAllUserCourse(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("course/{userId}")
	public Object getCourseListByUserId(@PathVariable int userId) {
		try {
			// kiểm tra user id gửi lên có tồn tại trong database hay không
			if (!userService.checkExistById(userId))
				return new ResponseEntity<Object>(userIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về danh sách course thuộc user id gửi lên
			return new ResponseEntity<Object>(userCourseService.getAllCourseByUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("user/{courseId}")
	public Object getUserListByCourseId(@PathVariable int courseId) {
		try {
			// kiểm tra course id gửi lên có tồn tại trong database hay không
			if (!courseService.checkExistById(courseId))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về danh sách user đang có course id gửi lên
			return new ResponseEntity<Object>(userCourseService.getAllUserByCourseId(courseId), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("")
	public Object post(@Valid @RequestBody AddUserCourseDto dto) {
		try {
			// kiểm tra user id gửi lên có tồn tại trong database hay không
			if (!userService.checkExistById(dto.getUserId()))
				return new ResponseEntity<Object>(userIsNotExist, HttpStatus.BAD_REQUEST);

			// kiểm tra course id gửi lên có tồn tại trong database hay không
			if (!courseService.checkExistById(dto.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// kiểm tra user này đã add course này chưa
			if (userCourseService.checkUserWithCourse(dto))
				return null;

			userCourseService.addCourse(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("{userId}")
	public Object put(@Valid @RequestBody AddUserCourseDto dto, @PathVariable int userId) {
		try {
			// kiểm tra user id gửi lên có tồn tại trong database hay không
			if (!userService.checkExistById(dto.getUserId()))
				return new ResponseEntity<Object>(userIsNotExist, HttpStatus.BAD_REQUEST);

			// kiểm tra course id gửi lên có tồn tại trong database hay không
			if (!courseService.checkExistById(dto.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// kiểm tra user này đã add course này chưa
			if (userCourseService.checkUserWithCourse(dto))
				return null;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{userId}")
	public Object delete(@Valid @RequestBody AddUserCourseDto dto, @PathVariable int userId) {
		try {
			// kiểm tra user id gửi lên có tồn tại trong database hay không
			if (!userService.checkExistById(dto.getUserId()))
				return new ResponseEntity<Object>(userIsNotExist, HttpStatus.BAD_REQUEST);

			// kiểm tra course id gửi lên có tồn tại trong database hay không
			if (!courseService.checkExistById(dto.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// kiểm tra user này đã add course này chưa
			if (userCourseService.checkUserWithCourse(dto))
				return null;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
