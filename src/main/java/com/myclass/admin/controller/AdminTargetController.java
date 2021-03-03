package com.myclass.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.TargetDto;
import com.myclass.service.TargetService;

/**
 * Author: Nguyen Chanh Truc Created: Jan 29, 2021
 */
@RestController
@RequestMapping("api/admin/target")
public class AdminTargetController {
	// Init message
	@Value("${message.targetIdNotExist}")
	private String targetIdNotExist;

	// Inject TargetService
	private TargetService targetService;

	public AdminTargetController(TargetService targetService) {
		this.targetService = targetService;
	}

	@GetMapping("")
	public Object get() {
		try {
			// Get all TargetDtos
			List<TargetDto> dtos = targetService.getAll();
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			// Get TargetDto by Id
			TargetDto dto = targetService.getById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("")
	public Object post(@RequestBody TargetDto dto) {
		try {
			targetService.save(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("{id}")
	public Object put(@PathVariable int id, @RequestBody TargetDto dto) {
		try {
			// Check path id and targetId
			if (id != dto.getId()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}

			// Check targetId exitence
			if (!targetService.isTargetIdExist(id)) {
				return new ResponseEntity<Object>(targetIdNotExist, HttpStatus.BAD_REQUEST);
			}

			targetService.edit(dto);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			// Check targetId exitence
			if (!targetService.isTargetIdExist(id)) {
				return new ResponseEntity<Object>(targetIdNotExist, HttpStatus.BAD_REQUEST);
			}

			targetService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
