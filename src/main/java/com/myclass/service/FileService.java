package com.myclass.service;

import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
public interface FileService {

	String upload(MultipartFile file, String uploadDir);

	Boolean deleteIfExists(String imageName, String uploadDir);
=======
/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 4, 2021	
 */
public interface FileService {
	
	String saveFile(MultipartFile file);
>>>>>>> 8a920ef59ff9fc96e7a96dbe3bfeeb0035b8686d

}
