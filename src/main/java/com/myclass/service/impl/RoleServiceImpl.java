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

	// Inject RoleRepository
	private RoleRepository roleRepository;	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void save(RoleDto dto) {
		// Convert from dto to entity
		Role entity = new Role();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		// Save entity to database
		roleRepository.save(entity);
	}

	@Override
	public List<RoleDto> getAll() {
		// Get all entities from database
		List<Role> entities = roleRepository.findAll();
		// Convert entities to dtos
		return entities.stream().map(entity -> convertEntityToDto(entity)).collect(Collectors.toList());
	}

	@Override
	public RoleDto getById(int id) {
		// Get entity from database
		Role entity = roleRepository.findById(id).get();
		// Convert entity to dto and return
		return convertEntityToDto(entity);
	}

	@Override
	public void edit(RoleDto dto) {
		// Get entity from database
		Role entity = roleRepository.findById(dto.getId()).get();
		// Check existence of entity
		if (entity == null) return;	
		// Edit properties
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		// Save entity
		roleRepository.save(entity);
	}

	@Override
	public void delete(int id) {
		roleRepository.deleteById(id);
	}
	
	/**
	 * Convert from entity to dto
	 * @param role
	 * @return RoleDto
	 */
	public RoleDto convertEntityToDto(Role role) {
		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setName(role.getName());
		dto.setDescription(role.getDescription());
		return dto;
	}

	@Override
	public Boolean isRoleNameExist(String roleName) {
		if (roleRepository.findByName(roleName) == null)
			return false;
		return true;
	}

	@Override
	public Boolean isRoleIdExist(int id) {
		return roleRepository.findById(id).isPresent();
	}
}
