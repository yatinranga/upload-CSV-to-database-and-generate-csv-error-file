package com.digilytics.assignment.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digilytics.assignment.dao.UserJpaDao;
import com.digilytics.assignment.entity.User;

@Component
public class UserDBWriter implements ItemWriter<User> {
	@Autowired
	UserJpaDao userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {

		System.out.println("Data Saved For Users : " + users);
		userRepository.saveAll(users);
	}
}
