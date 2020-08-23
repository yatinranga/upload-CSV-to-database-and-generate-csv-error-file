package com.digilytics.assignment.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digilytics.assignment.entity.User;

@Repository
public interface UserJpaDao extends JpaRepository<User, Integer> {

	Integer findIdByEmail(String email);

	@Query(value = "select email from User where active = ?1")
	Set<String> findAllByActive(Boolean active);

}
