package com.digilytics.assignment.batch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digilytics.assignment.dao.RoleJpaDao;
import com.digilytics.assignment.dao.UserJpaDao;
import com.digilytics.assignment.entity.Role;
import com.digilytics.assignment.entity.User;
import com.digilytics.assignment.entity.view.RoleResponse;
import com.digilytics.assignment.entity.view.UserRequest;

@Component
public class UserProcessor implements ItemProcessor<UserRequest, User> {

	@Autowired
	RoleJpaDao roleDao;

	@Autowired
	UserJpaDao userDao;

	@Value("${file.upload-dir}")
	private String errorFilePath;

	public static String errors = null;
	public static int rowParsed = 0, rowFailed = 0;

	Set<String> emails = null;
	List<RoleResponse> roleResponseList = null;
	Map<String, Integer> idNameMap = new HashMap<String, Integer>();

	public static FileOutputStream createErrorFile(String path, String fileName, UserRequest userRequest) {

		FileOutputStream errorFile = null;
		try {
			errorFile = new FileOutputStream(path + fileName, true);
			String s = "";

			s = userRequest.getEmail() + "," + userRequest.getName() + "," + userRequest.getRoles();

			errorFile.write(s.getBytes());
		} catch (IOException e) {
			System.err.println(e.getMessage() + "Error File Not Created");
		}
		return errorFile;

	}

	public UserRequest validateUser(UserRequest userRequest) throws IOException {

		FileOutputStream errorFile = null;
		String tempErrors = null;
		Boolean flag = false;

		if (userRequest.getEmail().isEmpty() || userRequest.getName().isEmpty() || userRequest.getRoles().isEmpty()) {
			errorFile = createErrorFile(errorFilePath, "errorFile.csv", userRequest);
			tempErrors = "Data is missing";
			errors = tempErrors;
			errorFile.write(tempErrors.getBytes());
			errorFile.write(10);
			errorFile.close();
			return null;
		}

		if (emails == null) {
			emails = userDao.findAllByActive(true);
		}
		if (roleResponseList == null) {
			roleResponseList = roleDao.findResponseByActive(true);

			for (RoleResponse role : roleResponseList) {
				idNameMap.put(role.getName(), role.getId());
			}

		}

		if (emails.contains(userRequest.getEmail())) {
			if (flag == false) {
				errorFile = createErrorFile(errorFilePath, "errorFile.csv", userRequest);
				flag = true;
			}
			tempErrors = "Email already exist";

			// return null;

		}
		List<String> requestedRoles = Arrays.asList(userRequest.getRoles().split("#"));
		Set<Role> finalRoles = new HashSet<Role>();

		for (String role : requestedRoles) {
			if (idNameMap.containsKey(role)) {
				Role r = new Role();
				r.setId(idNameMap.get(role));
				finalRoles.add(r);

			} else {
				if (flag == false) {
					errorFile = createErrorFile(errorFilePath, "errorFile.csv", userRequest);
					tempErrors = String.format(", Invaid Role %s", role);
					flag = true;
				} else {
					tempErrors = tempErrors + "#" + String.format(", Invaid Role %s ", role);
				}

				// return null;
			}
		}

		if (tempErrors != null) {
			errors = tempErrors;
			errorFile.write(tempErrors.getBytes());
			errorFile.write(10);
			errorFile.close();
			return null;
		}

		userRequest.setFinalRoles(finalRoles);
		emails.add(userRequest.getEmail());

		return userRequest;

	}

	@Override
	public User process(UserRequest userRequest) throws Exception {

		userRequest = validateUser(userRequest);

		if (userRequest == null) {
			rowFailed++;
			return null;
		}

		rowParsed++;
		User user = new User();
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setRoles(userRequest.getFinalRoles());
		return user;
	}

}
