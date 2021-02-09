package com.myclass.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String upload(MultipartFile file, String uploadDir);

	Boolean deleteIfExists(String imageName, String uploadDir);

}
