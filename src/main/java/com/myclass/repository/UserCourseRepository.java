package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.dto.UserCourseDto;
import com.myclass.entity.UserCourses;
import com.myclass.entity.UserCourseKey;

@Repository
public interface UserCourseRepository extends BaseRepository<UserCourses, UserCourseKey> {

}
