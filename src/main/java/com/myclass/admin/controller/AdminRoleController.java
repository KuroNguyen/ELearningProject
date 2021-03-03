package com.myclass.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;

@RestController
@RequestMapping("api/admin/role")
@CrossOrigin("*")
public class AdminRoleController {
	// Init message
	@Value("${message.roleNameExist}")
	private String roleNameExist;
	@Value("${message.roleIdNotExist}")
	private String roleIdNotExist;

	// Inject RoleService
	private RoleService roleService;

	public AdminRoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("")
	public Object get() {
		try {
			List<RoleDto> dtos = roleService.getAll();
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			RoleDto entity = roleService.getById(id);
			return new ResponseEntity<Object>(entity, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("")
	public Object post(@RequestBody RoleDto role) {
		try {
			// Check existence for roleName
			if (roleService.isRoleNameExist(role.getName()))
				return new ResponseEntity<Object>(roleNameExist, HttpStatus.BAD_REQUEST);
			roleService.save(role);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody RoleDto role) {
		try {
			// Check path id and roleId
			if (id != role.getId()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			// Check roleId existence
			if (!roleService.isRoleIdExist(id)) {
				return new ResponseEntity<Object>(roleIdNotExist, HttpStatus.BAD_REQUEST);
			}

			roleService.edit(role);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			// Check roleId existence
			if (!roleService.isRoleIdExist(id)) {
				return new ResponseEntity<Object>(roleIdNotExist, HttpStatus.BAD_REQUEST);
			}

			roleService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
