package com.myclass.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 4, 2021	
 */
public interface FileService {
	
	String saveFile(MultipartFile file);

}
