package com.myclass.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.service.FileService;

@RestController
@RequestMapping("apt/file")
public class FileController {
	private FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@Value("${file.upload-dir}")
	private String uploadDir;

	@PostMapping("upload")
	public Object upload(@RequestParam MultipartFile file) {
		try {
			String result = fileService.upload(file, uploadDir);

			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{imageName}")
	public Object delete(@PathVariable String imageName) {
		try {
			if (fileService.deleteIfExists(imageName, uploadDir))
				return new ResponseEntity<Object>(HttpStatus.OK);

			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
