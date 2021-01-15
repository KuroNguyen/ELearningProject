package com.myclass.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	private RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public void save(RoleDto dto) {
		Role entity = new Role();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		
		roleRepository.save(entity);
	}

	@Override
	public List<RoleDto> getAll() {
		List<Role> entities = roleRepository.findAll();
		return entities.stream().map(entity -> convertEntityToDto(entity)).collect(Collectors.toList());
	}

	@Override
	public RoleDto getById(int id) {
		Role entity = roleRepository.findById(id).get();
		return convertEntityToDto(entity);
	}

	@Override
	public void edit(RoleDto dto) {
		Role entity = roleRepository.findById(dto.getId()).get();
		if (entity != null) {
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			
			roleRepository.save(entity);
		}
	}

	@Override
	public void delete(int id) {
		roleRepository.deleteById(id);
	}
	
	public RoleDto convertEntityToDto(Role role) {
		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setName(role.getName());
		dto.setDescription(role.getDescription());
		return dto;
	}
}
