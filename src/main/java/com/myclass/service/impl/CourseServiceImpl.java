package com.myclass.service.impl;

import java.lang.reflect.Field;
import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.myclass.dto.AddCourseDto;
import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.BuyCourseDto;
import com.myclass.dto.CourseDetailDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.MenuCourseDto;
import com.myclass.dto.TargetDto;
import com.myclass.dto.UserCourseDto;
import com.myclass.dto.VideoDto;
import com.myclass.entity.Course;
import com.myclass.entity.Target;
import com.myclass.entity.User;
import com.myclass.entity.UserCourseKey;
import com.myclass.entity.UserCourses;
import com.myclass.entity.Video;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.CourseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {
	// Inject CourseRepository
	private CourseRepository courseRepository;
	private UserCourseRepository userCourseRepository;
	private UserRepository userRepository;

	public CourseServiceImpl(UserCourseRepository userCourseRepository, UserRepository userRepository,
			CourseRepository courseRepository) {
		this.userCourseRepository = userCourseRepository;
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public List<CourseDto> getAllWithCategory() {
		// trả về danh sách course có category
		return courseRepository.getAllWithCategory();
	}

	@Override
	public void add(AddCourseDto dto) {
		// mapping dto to entity
		Course entity = new Course();
		entity.setId(0);
		entity.setTitle(dto.getTitle());
		entity.setImage(dto.getImage());
		entity.setLecturesCount(dto.getLecturesCount());
		entity.setHourCount(dto.getHourCount());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setPromotionPrice(dto.getPromotionPrice());
		entity.setDescription(dto.getDescription());
		entity.setContent(dto.getContent());
		entity.setCategoryId(dto.getCategoryId());
		entity.setLastUpdate(new Date());

		// thêm course mới vào database
		courseRepository.save(entity);
	}

	@Override
	public void edit(CourseDto dto) {
		// Get entity from database
		Course entity = courseRepository.findById(dto.getId()).get();
	
		entity.setTitle(dto.getTitle());
		entity.setImage(dto.getImage());
		entity.setLecturesCount(dto.getLecturesCount());
		entity.setHourCount(dto.getHourCount());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setPromotionPrice(dto.getPromotionPrice());
		entity.setDescription(dto.getDescription());
		entity.setContent(dto.getContent());
		entity.setCategoryId(dto.getCategoryId());
		entity.setLastUpdate(new Date());
		// Save to database
		courseRepository.save(entity);
	}

	@Override
	public void deleteById(int id) {
		// Delete course by id
		courseRepository.deleteById(id);
	}

	@Override
	public CourseDto getCourseById(int id) {
		// Get entity from database
		Course entity = courseRepository.findById(id).get();
		// Convert from entity to dto
		CourseDto dto = new CourseDto();
		dto.setId(id);
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLecturesCount(entity.getLecturesCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setPrice((float) entity.getPrice());
		dto.setDiscount((int) entity.getPrice());
		dto.setPromotionPrice((float) entity.getPromotionPrice());
		dto.setDescription(entity.getDescription());
		dto.setContent(entity.getContent());
		dto.setCategoryId(entity.getCategoryId());
		dto.setCategoryName(entity.getCategory().getTitle());
		dto.setLastUpdate(entity.getLastUpdate());

		return dto;
	}

	@Override
	public List<MenuCourseDto> getMenuCourseByCategoryId(int id) {
		// chuyển entity sang dto
		List<MenuCourseDto> menuCourseDtoList = new ArrayList<MenuCourseDto>();
		List<Course> courseList = courseRepository.findAllByCategoryId(id);
		for (Course course : courseList) {
			MenuCourseDto dto = new MenuCourseDto(course.getId(), course.getTitle(), course.getCategoryId());
			menuCourseDtoList.add(dto);
		}
		return menuCourseDtoList;
	}

	@Override
	public boolean checkExistByTitle(String title) {
		// kiểm tra xem course title có tồn tại dưới database chưa
		if (courseRepository.findByTitle(title) == null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistById(int id) {
		// kiểm tra xem course id có tồn tại dưới database chưa
		return courseRepository.findById(id).isPresent();
	}

	@Override
	public CourseDto findCourseDtoById(int id) {
		// trả về dto theo id gửi lên
		return courseRepository.getCourseDtoWithCategoryById(id);
	}

	@Override
	public Page<Course> findAllPaging(String orderBy, int pageIndex, int pageSize, boolean descending) {
		if (descending)
			return courseRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by(orderBy).descending()));

		return courseRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by(orderBy)));
	}

	@Override
	public boolean checkProperty(String orderBy) {
		Field[] properties = new Course().getClass().getDeclaredFields();
		for (Field field : properties) {
			if (field.toString().endsWith(orderBy))
				return true;
		}
		return false;
	}

	@Override
	public String getImageById(int id) {
		return courseRepository.findById(id).get().getImage();
	}

	@Override
	public void editImageById(int id, String image) {
		Course course = courseRepository.findById(id).get();
		course.setImage(image);
		courseRepository.save(course);
	}

	public void addCourse(AddUserCourseDto dto) {
		User user = userRepository.findById(dto.getUserId()).get();
		Course course = courseRepository.findById(dto.getCourseId()).get();
		UserCourseKey key = new UserCourseKey(dto.getUserId(), dto.getCourseId());
		UserCourses userCourse = new UserCourses(key, user, course, user.getRoleId());
		userCourseRepository.save(userCourse);
	}

	public List<UserCourseDto> getAllUserCourse() {
		List<UserCourses> userCourseList = userCourseRepository.findAll();
		List<UserCourseDto> userCourseDtoList = new ArrayList<UserCourseDto>();
		for (UserCourses userCourse : userCourseList) {
			UserCourseDto userCourseDto = new UserCourseDto(userCourse.getUser().getEmail(),
					userCourse.getCourse().getTitle());
			userCourseDtoList.add(userCourseDto);
		}
		return userCourseDtoList;
	}

	public List<UserCourseDto> getAllCourseByUserId(int userId) {
		List<UserCourses> userCourseList = userCourseRepository.findAll();
		List<UserCourseDto> userCourseDtoList = new ArrayList<UserCourseDto>();
		for (UserCourses userCourse : userCourseList) {
			if (userCourse.getId().getUserId() == userId) {
				UserCourseDto userCourseDto = new UserCourseDto(userCourse.getUser().getEmail(),
						userCourse.getCourse().getTitle());
				userCourseDtoList.add(userCourseDto);
			}
		}
		return userCourseDtoList;
	}

	public List<UserCourseDto> getAllUserByCourseId(int courseId) {
		List<UserCourses> userCourseList = userCourseRepository.findAll();
		List<UserCourseDto> userCourseDtoList = new ArrayList<UserCourseDto>();
		for (UserCourses userCourse : userCourseList) {
			if (userCourse.getId().getCourseId() == courseId) {
				UserCourseDto userCourseDto = new UserCourseDto(userCourse.getUser().getEmail(),
						userCourse.getCourse().getTitle());
				userCourseDtoList.add(userCourseDto);
			}
		}
		return userCourseDtoList;
	}

	public boolean checkUserWithCourse(AddUserCourseDto dto) {
		UserCourseKey key = new UserCourseKey(dto.getUserId(), dto.getCourseId());
		return userCourseRepository.findById(key).isPresent();
	}

	@Override
	public CourseDetailDto getCourseDetailsById(int id) {
		// Get entity from database
		Course entity = courseRepository.findById(id).get();
		// Convert from entity to dto
		CourseDetailDto dto = new CourseDetailDto();
		dto.setId(id);
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLecturesCount(entity.getLecturesCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setPrice((float) entity.getPrice());
		dto.setDiscount((int) entity.getPrice());
		dto.setPromotionPrice((float) entity.getPromotionPrice());
		dto.setDescription(entity.getDescription());
		dto.setContent(entity.getContent());
		dto.setCategoryId(entity.getCategoryId());
		dto.setCategoryName(entity.getCategory().getTitle());
		dto.setLastUpdate(entity.getLastUpdate());
		// Set targets
		List<TargetDto> targetList = entity.getTargets().stream().map(target -> convertTargetToTargetDto(target))
				.collect(Collectors.toList());
		dto.setTargets(targetList);
		// Set videos
		List<VideoDto> videoList = entity.getVideos().stream().map(video -> convertVideoToVideoDto(video))
				.collect(Collectors.toList());
		dto.setVideos(videoList);

		return dto;
	}

	private TargetDto convertTargetToTargetDto(Target entity) {
		TargetDto dto = new TargetDto();
		dto.setCourseId(entity.getCourseId());
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		return dto;
	}

	private VideoDto convertVideoToVideoDto(Video entity) {
		VideoDto dto = new VideoDto();
		dto.setCourseId(entity.getCourseId());
		dto.setTitle(entity.getTitle());
		dto.setId(entity.getId());
		dto.setTimeCount(entity.getTimeCount());
		dto.setUrl(entity.getUrl());
		return dto;
	}

	@Override
	public void buyCourse(BuyCourseDto dto, UserDetails userDetails) {
		// Get current user email from securityHolderContext
		String userEmail = userDetails.getUsername();
		// Attach user for each course in BuyCourseDto
		dto.getCourseDto().stream()
			.forEach(courseDto -> {
				UserCourses entity = new UserCourses();
				UserCourseKey key = new UserCourseKey();
				entity.setId(key);
				entity.setCourse(courseRepository.findById(courseDto.getId()).get());
				entity.setUser(userRepository.findByEmail(userEmail));
				
				userCourseRepository.save(entity);
			});
		
		
	}

	@Override
	public List<CourseDto> getAllByCategoryId(int id) {
		return courseRepository.getAllByCategoryId(id);
	}
}