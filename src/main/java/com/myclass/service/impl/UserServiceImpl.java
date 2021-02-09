package com.myclass.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.UserDto;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public void insert(UserDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(UserDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkExistById(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
