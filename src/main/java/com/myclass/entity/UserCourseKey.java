package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseKey implements Serializable{

	@Column(name = "user_id")
	public int userId;
	
	@Column(name = "course_id")
	public int courseId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCourseKey other = (UserCourseKey) obj;
		if (courseId != other.courseId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
		result = prime * result + userId;
		return result;
	}

	
}
