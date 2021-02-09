package com.myclass.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.EditCourseDto;
import com.myclass.dto.MenuCourseDto;
import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseServiceImpl implements CourseService {
	private CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<CourseDto> getAllWithCategory() {
		// trả về danh sách course có category
		return courseRepository.getAllWithCategory();
	}

	public void add(AddCourseDto entity) {
		// thêm course mới vào database
		courseRepository.save(new Course(0, entity.getTitle(), "", entity.getLecturesCount(), 0, 0, entity.getPrice(),
				entity.getDiscount(), entity.getPromotionPrice(), entity.getDescription(), entity.getContent(),
				entity.getCategoryId(), new Date()));
	}

	public void deleteById(int id) {
		// xóa course theo id gửi lên
		courseRepository.deleteById(id);
	}

	public void edit(EditCourseDto entity) {
		// sửa course dưới database
		Course course = courseRepository.findById(entity.getId()).get();
		
		courseRepository.saveAll(new Course(entity.getId(), entity.getTitle(), course.getImage(), entity.getLecturesCount(),
				course.getHourCount(), course.getViewCount(), entity.getPrice(), entity.getDiscount(),
				entity.getPromotionPrice(), entity.getDescription(), entity.getContent(), entity.getCategoryId(),
				new Date()));
	}

	public EditCourseDto getCourseById(int id) {
		// chuyển entity sang dto
		Course entity = courseRepository.findById(id).get();
		return new EditCourseDto(entity.getId(), entity.getTitle(), entity.getLecturesCount(), entity.getPrice(),
				entity.getDiscount(), entity.getPromotionPrice(), entity.getDescription(), entity.getContent(),
				entity.getCategoryId());
	}

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

	public boolean checkExistByTitle(String title) {
		// kiểm tra xem course title có tồn tại dưới database chưa
		if (courseRepository.findByTitle(title) == null)
			return false;
		return true;
	}

	public boolean checkExistById(int id) {
		// kiểm tra xem course id có tồn tại dưới database chưa
		return courseRepository.findById(id).isPresent();
	}

	public CourseDto findCourseDtoById(int id) {
		// trả về dto theo id gửi lên
		return courseRepository.getCourseDtoWithCategoryById(id);
	}

	public Page<Course> findAllPaging(String orderBy, int pageIndex, int pageSize, boolean descending) {
		if (descending)
			return courseRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by(orderBy).descending()));

		return courseRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by(orderBy)));
	}

	public boolean checkProperty(String orderBy) {
		Field[] properties = new Course().getClass().getDeclaredFields();
		for (Field field : properties) {
			if (field.toString().endsWith(orderBy))
				return true;
		}
		return false;
	}

	public String getImageById(int id) {
		return courseRepository.findById(id).get().getImage();
	}

	public void editImageById(int id, String image) {
		Course course = courseRepository.findById(id).get();
		course.setImage(image);
		courseRepository.save(course);
	}

}
