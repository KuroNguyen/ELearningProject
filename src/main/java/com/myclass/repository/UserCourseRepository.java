package com.myclass.repository;

import java.util.List;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.dto.UserCourseDto;
import com.myclass.entity.UserCourses;
import com.myclass.entity.UserCourseKey;

@Repository
public interface UserCourseRepository extends BaseRepository<UserCourses, UserCourseKey> {

}
=======
import com.myclass.entity.UserCourses;
import com.myclass.service.impl.UserServiceImpl;

public interface UserCourseRepository {

	void save(UserCourses userCourse);

	List<UserCourses> findAll();

}
>>>>>>> 268f890b983c47dab7035bf8426985b9668a65df
