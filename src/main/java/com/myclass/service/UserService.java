package com.myclass.service;

import java.util.List;

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

	boolean checkExistById(int userId);

	UserDto checkMail(String email);

	void signUp(SignUpDto dto);

	boolean checkExistByEmail(String email);

	boolean checkExistByPhone(String phone);

	UserLoginDto getUserLoginDtoByEmail(String email);

	boolean checkPassword(String email, String oldPassword);

	void changePassword(String email, String newPassword);

	void setNewPassword(int id, String newPassword);

	String getAvatarById(int id);

	void editAvatarById(int id, String image);

	String getAvatarByEmail(String email);

	UserInfoDto getInfoByEmail(String email);

	void editAvatarByEmail(String email, String upload);
	
	// error 
	void editProfile(UserDto dto);
	
	void editPassword(UserDto dto);

}
