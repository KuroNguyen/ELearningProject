package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.myclass.dto.SignUpDto;
import com.myclass.dto.UserDto;
import com.myclass.dto.UserInfoDto;
import com.myclass.dto.UserLoginDto;
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
		// kiểm tra xem user id có tồn tại dưới database chưa
				return userRepository.findById(userId).isPresent();
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

	public void signUp(SignUpDto entity) {
		// đăng ký tài khoản user thì sẽ có role là student
		userRepository.save(new User(0, entity.getEmail(), entity.getFullname(),
				BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()), "", entity.getAddress(), entity.getPhone(), 3));
	}


	public boolean checkExistByEmail(String email) {
		// kiểm tra xem user email có tồn tại dưới database chưa
		if (userRepository.findByEmail(email) == null)
			return false;
		return true;
	}

	public boolean checkExistByPhone(String phone) {
		// kiểm tra xem user sdt có tồn tại dưới database chưa
		if (userRepository.findByPhone(phone) == null)
			return false;
		return true;
	}
	public UserLoginDto getUserLoginDtoByEmail(String email) {
		// chuyển từ entity sang dto
		User user = userRepository.findByEmail(email);
		return new UserLoginDto(user.getId(), user.getEmail(), user.getFullname(), user.getAvatar(), user.getPhone(),
				user.getAddress(), user.getRole().getDescription());
	}

	public boolean checkPassword(String email, String oldPassword) {
		User user = userRepository.findByEmail(email);
		return BCrypt.checkpw(oldPassword, user.getPassword());
	}
	public void changePassword(String email, String newPassword) {
		User user = userRepository.findByEmail(email);
		user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
		userRepository.save(user);
	}

	public void setNewPassword(int id, String newPassword) {
		User user = userRepository.findById(id).get();
		user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
		userRepository.save(user);
	}

	public String getAvatarById(int id) {
		return userRepository.findById(id).get().getAvatar();
	}
	public void editAvatarById(int id, String image) {
		User user = userRepository.findById(id).get();
		user.setAvatar(image);
		userRepository.save(user);
	}

	public String getAvatarByEmail(String email) {
		return userRepository.findByEmail(email).getAvatar();
	}
	public UserInfoDto getInfoByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return new UserInfoDto(user.getFullname(),user.getAvatar(),user.getPhone(),user.getAddress());
	}

	public void editAvatarByEmail(String email, String upload) {
		User user = userRepository.findByEmail(email);
		user.setAvatar(upload);
		userRepository.save(user);
	}


}
