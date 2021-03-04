package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddUserDto;
import com.myclass.dto.EditUserDto;
import com.myclass.dto.SignUpDto;
import com.myclass.dto.UserDto;
import com.myclass.dto.UserInfoDto;
import com.myclass.dto.UserLoginDto;

public interface UserService {
	void insert(UserDto dto);
	List<UserDto> getAll();
	UserDto getById(int id);
	UserDto getByEmail(String email);
	void edit(UserDto dto);
	void delete(int id);

	List<UserDto> getAllUserWithRole();

	EditUserDto getUserById(int id);

	void deleteById(int id);

	void add(AddUserDto entity);

	void edit(EditUserDto entity);

	void signUp(SignUpDto dto);

	boolean checkExistById(int id);

	boolean checkExistByEmail(String email);

	boolean checkExistByPhone(String phone);

	UserLoginDto getUserLoginDtoByEmail(String email);

	boolean checkPassword(String email, String oldPassword);

	void changePassword(String email, String newPassword);

	void setNewPassword(int id, String newPassword);

	String getAvatarById(int id);

	void editAvatarById(int id, String image);

	UserInfoDto getInfoByEmail(String email);

	String getAvatarByEmail(String email);

	void editAvatarByEmail(String email, String upload);
}
