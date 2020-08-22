package com.digilytics.assignment.service;

import org.springframework.core.io.Resource;

public interface UserService {

	/**
	 * this method is used for downloading error file
	 * 
	 * @param file
	 * @return {@link Resource}
	 */
	Resource downloadErrorFileAsResource(String file);
}
