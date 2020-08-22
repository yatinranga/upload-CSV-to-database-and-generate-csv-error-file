package com.digilytics.assignment.entity.view;

import com.digilytics.assignment.entity.Role;

public class RoleResponse {

	public String name;

	public Integer id;

	public RoleResponse(Integer id, String name) {
		this.id = id;
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static RoleResponse get(Role role) {
		return new RoleResponse(role.getId(), role.getName());
	}

}
