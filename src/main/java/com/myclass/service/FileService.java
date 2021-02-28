package com.myclass.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 4, 2021	
 */
public interface FileService {
	
	String upload(MultipartFile file, String uploadDir);

	Boolean deleteIfExists(String imageName, String uploadDir);
	
	String saveFile(MultipartFile file);

}
