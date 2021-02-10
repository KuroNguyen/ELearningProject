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
	
	@Value("${context-path}")
	private String contextPath;

	@Value("${dir-course}")
	private String course;

	@Value("${dir-user}")
	private String user;
	
	private final String userDirectory = Paths.get("").toAbsolutePath().toString();

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

	@Override
	public String upload(MultipartFile file, String uploadDir) {
		try {
			String fileName = file.getOriginalFilename();

			Path path = Paths.get(userDirectory + uploadDir + fileName);

			if (uploadDir.endsWith(course) || uploadDir.endsWith(user)) {
				path = Paths.get(userDirectory + uploadDir + "/" + fileName);
			}

			Files.write(path, file.getBytes());

			if (uploadDir.endsWith(course))
				return contextPath + course + "/" + fileName;

			if (uploadDir.endsWith(user))
				return contextPath + user + "/" + fileName;

			return contextPath + fileName;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deleteIfExists(String imageName, String uploadDir) {
		try {
			Path path = Paths.get(userDirectory + uploadDir + imageName);

			if (uploadDir.endsWith(course) || uploadDir.endsWith(user)) {
				path = Paths.get(userDirectory + uploadDir + "/" + imageName);
			}

			return Files.deleteIfExists(path);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
