package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("Select u from User u where u.email = :email")
	public User custom(@Param("email") String email);
	
	public User findByEmail(String email);
	public User findByPhone(String phone);
//	@Query("SELECT new com.myclass.dto.UserDto(u.id, u.fullname, u.email, u.phone, u.address, r.description) FROM users u join roles r on u.role_id = r.id")
//	public List<UserDto> findAllJoin(); 
	
	
	

}
