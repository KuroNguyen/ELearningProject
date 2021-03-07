package com.myclass.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.PasswordDto;
import com.myclass.dto.SignUpDto;
import com.myclass.dto.UserDto;
import com.myclass.service.CourseService;
import com.myclass.service.FileService;
import com.myclass.service.CourseService;
import com.myclass.service.UserService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("api/human")
public class UserController {
	private UserService userService;
	private CourseService courseService;
	
	private FileService fileService;

	public UserController(UserService userService, CourseService courseService,
			FileService fileService) {
		this.userService = userService;
		this.courseService = courseService;
		
		this.fileService = fileService;
	}

	@Value("${message.course}")
	private String courseIsNotExist;

	@Value("${message.email}")
	private String emailIsExist;

	@Value("${message.phone}")
	private String phoneIsExist;

	@Value("${message.username}")
	private String emailIsNotExist;

	@Value("${message.user}")
	private String userIsNotExist;

	@Value("${message.password}")
	private String passwrodIsNotCorrect;

	@Value("${message.token}")
	private String tokenIsNotCorrect;

	@Value("${message.token.exp}")
	private String tokenIsExpiration;

	@Value("${secret}")
	private String secretKey;

	@Value("${bearer}")
	private String bearer;

	@Value("${file.upload-user}")
	private String uploadDir;

	@PostMapping("signup")
	public Object signUp(@Valid @RequestBody SignUpDto dto) {
		try {
			// check xem email có tồn tại hay không
			if (userService.checkExistByEmail(dto.getEmail()))
				return new ResponseEntity<Object>(emailIsExist, HttpStatus.BAD_REQUEST);

			// check xem sdt có tồn tại hay không
			if (userService.checkExistByPhone(dto.getPhone()))
				return new ResponseEntity<Object>(phoneIsExist, HttpStatus.BAD_REQUEST);

			// đăng ký tài khoản mới
			userService.signUp(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("changePassword")
	public Object changePassword(@Valid @RequestBody PasswordDto dto) {
		try {
			// check xem email có tồn tại hay không
			if (!userService.checkExistByEmail(dto.getEmail()))
				return new ResponseEntity<Object>(emailIsNotExist, HttpStatus.BAD_REQUEST);

			// check password cũ gửi lên có đúng hay không
			if (userService.checkPassword(dto.getEmail(), dto.getOldPassword()))
				return new ResponseEntity<Object>(passwrodIsNotCorrect, HttpStatus.BAD_REQUEST);

			// thay đổi password trong database thành password mới
			userService.changePassword(dto.getEmail(), dto.getNewPassword());
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("addCourse")
	public Object userAddCourse(@Valid @RequestBody AddUserCourseDto dto,
			@RequestHeader(name = "Authorization") String token) {
		try {
			// check xem email có tồn tại hay không
			if (!userService.checkExistById(dto.getUserId()))
				return new ResponseEntity<Object>(emailIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(dto.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem user này đã add course này chưa
			if (courseService.checkUserWithCourse(dto))
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

			courseService.addCourse(dto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{email}")
	public Object getInfo(@PathVariable String email, @RequestHeader(name = "Authorization") String tokenHeader) {
		try {
			if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith(bearer))
				return new ResponseEntity<Object>(tokenIsNotCorrect, HttpStatus.BAD_REQUEST);

			String token = tokenHeader.replace(bearer, "");

			String emailToken = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

			if (!email.equals(emailToken))
				return new ResponseEntity<Object>(tokenIsNotCorrect, HttpStatus.BAD_REQUEST);

			Date exp = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
			Date now = new Date();

			if (exp.before(now))
				return new ResponseEntity<Object>(tokenIsExpiration, HttpStatus.BAD_REQUEST);

			return new ResponseEntity<Object>(userService.getInfoByEmail(email), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("getAvatar/{email}")
	public Object getAvatar(@PathVariable String email, @RequestHeader(name = "Authorization") String tokenHeader) {
		try {
			if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith(bearer))
				return new ResponseEntity<Object>(tokenIsNotCorrect, HttpStatus.BAD_REQUEST);

			String token = tokenHeader.replace(bearer, "");

			String emailToken = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

			if (!email.equals(emailToken))
				return new ResponseEntity<Object>(tokenIsNotCorrect, HttpStatus.BAD_REQUEST);

			Date exp = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
			Date now = new Date();

			if (exp.before(now))
				return new ResponseEntity<Object>(tokenIsExpiration, HttpStatus.BAD_REQUEST);

			return new ResponseEntity<Object>(userService.getAvatarByEmail(email), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("editAvatar/{email}")
	public Object editAvatar(@RequestParam MultipartFile file,@PathVariable String email, @RequestHeader(name = "Authorization") String tokenHeader) {
		try {
			if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith(bearer))
				return new ResponseEntity<Object>(tokenIsNotCorrect, HttpStatus.BAD_REQUEST);

			String token = tokenHeader.replace(bearer, "");

			String emailToken = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

			if (!email.equals(emailToken))
				return new ResponseEntity<Object>(tokenIsNotCorrect, HttpStatus.BAD_REQUEST);

			Date exp = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
			Date now = new Date();

			if (exp.before(now))
				return new ResponseEntity<Object>(tokenIsExpiration, HttpStatus.BAD_REQUEST);
			
			userService.editAvatarByEmail(email,fileService.upload(file, uploadDir));
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("profile/{id}")
	public Object putProfile(@PathVariable int id, @RequestBody UserDto userDto) {
		try {
			System.out.println("Profile: " + userDto.toString());
			if (id != userDto.getId()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			userService.editProfile(userDto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("password/{id}")
	public Object putPassword(@PathVariable int id, @RequestBody UserDto userDto) {
		try {
			System.out.println("Pasword: " + userDto.toString());
			if (id != userDto.getId()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			userService.editPassword(userDto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}