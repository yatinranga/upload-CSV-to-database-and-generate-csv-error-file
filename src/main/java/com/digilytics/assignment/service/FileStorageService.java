package com.digilytics.assignment.service;

import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.digilytics.assignment.ex.NotFoundException;
import com.digilytics.assignment.ex.ValidationException;

public interface FileStorageService {
	public static Resource fetchFile(String filePath) {
		try {
			// System.out.println(String.format("File fetch from url (%s)", filePath));
			Resource resource = new UrlResource(Paths.get(filePath).toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new NotFoundException("File not found");
			}
		} catch (MalformedURLException e) {
			throw new ValidationException("Error in reading file");
		}
	}
}
