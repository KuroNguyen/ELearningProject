package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository; 
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		// GỌI PHƯƠNG THỨC TRUY VẤN LẤY DANH SÁCH USER
		List<User> entities = userRepository.findAll();
		// MAPPING USER ENTITY SANG USER DTO
		for (User entity : entities) {
			// GỌI PHƯƠNG THỨC TRUY VẤN LẤY ROLE THEO id 
			// (roleId là khóa ngoại lưu trong bảng user)
			Role role = roleRepository.findById(entity.getRoleId()).get();
			UserDto dto = new UserDto(
					entity.getId(),
					entity.getEmail(),
					
					entity.getFullname(),
					entity.getPassword(),
					entity.getRoleId()
				);
			dto.setRoleDesc(role.getDescription());
			dtos.add(dto);
		}
		
//		List<UserDto> dtos = userRepository.findAllJoin();
		return dtos;
	}

	@Override
	public void insert(UserDto dto) {
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		// MAPPING USER DTO SANG USER ENTITY
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashed);
		entity.setFullname(dto.getFullname());
		entity.setAvatar(dto.getAvatar());
		entity.setRoleId(2);
		
		userRepository.save(entity);
	}
	
	@Override
	public void edit(UserDto dto) {
		// TRUY VẤN LẤY RA DỮ LIỆU ĐANG LƯU TRONG DB
		User entity = userRepository.findById(dto.getId()).get();
		// MAPPING USER DTO SANG USER ENTITY
		entity.setEmail(dto.getEmail());
		entity.setFullname(dto.getFullname());
		entity.setAvatar(dto.getAvatar());
		entity.setRoleId(dto.getRoleId());
		// NẾU NGƯỜI DÙNG NHẬP MẬT KHẨU MỚI THÌ ĐỔI LẠI MẬT KHẨU
		if(entity.getPassword() != null && !entity.getPassword().isEmpty()) {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			entity.setPassword(hashed);
		}
		userRepository.save(entity);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDto getById(int id) {
		User entity = userRepository.findById(id).get();
		UserDto dto = new UserDto(
				entity.getId(),
				entity.getEmail(),
				entity.getFullname(),
				entity.getPassword(),
				entity.getAvatar(),
				entity.getPhone(), entity.getAddress(), entity.getRoleId()
			);
		return dto;
	}

	@Override
	public boolean checkExistById(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto getByEmail(String email) {
		User entity = userRepository.findByEmail(email);
		UserDto dto = new UserDto(
				entity.getId(),
				entity.getEmail(),
				entity.getPassword(),
				entity.getFullname(),
				entity.getAvatar(),
				entity.getPhone(), entity.getAddress(), entity.getRoleId()
			);
		return dto;
	}


}
