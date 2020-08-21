package com.digilytics.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digilytics.assignment.entity.Role;

@Repository
public interface RoleJpaDao extends JpaRepository<Role, Integer> {

}
