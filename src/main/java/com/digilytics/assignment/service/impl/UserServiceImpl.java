package com.digilytics.assignment.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.digilytics.assignment.service.FileStorageService;
import com.digilytics.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${file.upload-dir}")
	private String errorFilePath;

	@Override
	public Resource downloadErrorFileAsResource(String file) {
		return FileStorageService.fetchFile(errorFilePath + file);

	}

}
