package com.digilytics.assignment.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digilytics.assignment.entity.User;

@Repository
public interface UserJpaDao extends JpaRepository<User, Integer> {

	Integer findIdByEmail(String email);

	Set<String> findAllByActiveTrue();

}
