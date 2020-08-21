package com.digilytics.assignment.batch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digilytics.assignment.dao.RoleJpaDao;
import com.digilytics.assignment.dao.UserJpaDao;
import com.digilytics.assignment.entity.Role;
import com.digilytics.assignment.entity.User;
import com.digilytics.assignment.entity.view.UserRequest;

@Component
public class UserProcessor implements ItemProcessor<UserRequest, User> {

//	@Autowired
//	RoleJpaDao roleDao;
//
//	@Autowired
//	UserJpaDao userDao;
//
//	List<Role> roleResponseList = roleDao.findAll();
//	Map<String, Integer> idNameMap = new HashMap<String, Integer>();
//
////	public UserProcessor() {
////		for (Role role : roleResponseList) {
////			idNameMap.put(role.getName(), role.getId());
////		}
////	}
//
//	public UserRequest validateUser(UserRequest userRequest) {
//		for (Role role : roleResponseList) {
//			idNameMap.put(role.getName(), role.getId());
//		}
//
//		if (userRequest.getEmail().isEmpty() || userRequest.getName().isEmpty() || userRequest.getRoles().isEmpty()) {
//			return null;
//		}
//
//		Integer userId = userDao.findIdByEmail(userRequest.getEmail());
//		if (userId != null) {
//			// throw new ValidationException(String.format("This user's email (%s) already
//			// exists", request.getEmail()));
////
////			if (flag == false) {
////				errorFile = createErrorFile(errorFilePath, "errorFile.csv", row);
////				flag = true;
////			}
////			tempErrors = "Email already exist";
//
//			return null;
//
//		}
//
//		List<String> requestedRoles = Arrays.asList(userRequest.getRoles().split("#"));
//
//		// Set<Integer> roleIds = new HashSet<Integer>();
//		Set<Role> finalRoles = new HashSet<Role>();
//
//		for (String role : requestedRoles) {
//			if (idNameMap.containsKey(role)) {
//				Role r = new Role();
//				r.setId(idNameMap.get(role));
//				finalRoles.add(r);
//
//				// roleIds.add(idNameMap.get(role));
//			} else {
////				if (flag == false) {
////					errorFile = createErrorFile(errorFilePath, "errorFile.csv", row);
////					tempErrors = String.format("Invaid Role %s", requestRole);
////					flag = true;
////				} else {
////					tempErrors = tempErrors + "#" + String.format("Invaid Role %s ", requestRole);
////				}
//
//				return null;
//
//			}
//		}
//
//		return userRequest;
//
//	}

	@Override
	public User process(UserRequest userRequest) throws Exception {
//
//		userRequest = validateUser(userRequest);
//		if (userRequest == null) {
//			return null;
//		}

		User user = new User();
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setRole(userRequest.getRoles());
		return user;
	}

}
