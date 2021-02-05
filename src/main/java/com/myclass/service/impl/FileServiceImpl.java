package com.myclass.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.service.FileService;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 4, 2021	
 */
@Service
public class FileServiceImpl implements FileService {
	
	@Value("${file.upload-dir}")
	private String uploadDir;

	@Override
	public String saveFile(MultipartFile file) {
		try {
			// Get fileName
			String fileName = file.getOriginalFilename();
			// Store file to folder
			Path filePath = Paths.get(uploadDir + fileName);
			Files.write(filePath, file.getBytes());
			
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
