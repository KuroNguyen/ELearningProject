package com.myclass.service.impl;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.MenuCourseDto;
import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {
	// Inject CourseRepository
	private CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
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

}
