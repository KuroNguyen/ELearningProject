package com.myclass.service;

import java.util.List;

import com.myclass.dto.UserDto;

public interface UserService {
	void insert(UserDto dto);
	List<UserDto> getAll();
	UserDto getById(int id);
	UserDto getByEmail(String email);
	void edit(UserDto dto);
	void delete(int id);
	boolean checkExistById(int userId);
}
