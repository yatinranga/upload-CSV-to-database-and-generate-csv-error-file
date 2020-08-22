package com.digilytics.assignment.entity.view;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.digilytics.assignment.entity.Role;

public class UserRequest {

	@NotNull(message = "User's name can't be null")
	private String name;

	@Email(message = "Email pattern isn't correct")
	@NotNull(message = "User's email can't be null")
	private String email;

	@NotNull(message = "roles can't be null")
	private String roles;

	private Set<Role> finalRoles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Set<Role> getFinalRoles() {
		return finalRoles;
	}

	public void setFinalRoles(Set<Role> finalRoles) {
		this.finalRoles = finalRoles;
	}

}
