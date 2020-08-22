package com.digilytics.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digilytics.assignment.entity.Role;
import com.digilytics.assignment.entity.view.RoleResponse;

@Repository
public interface RoleJpaDao extends JpaRepository<Role, Integer> {

	List<RoleResponse> findResponseByActive(Boolean active);

	RoleResponse findIdByNameAndActive(String name, boolean b);

}
