package com.digilytics.assignment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "modified_at")
	private Date modifiedAt;

	@NotNull
	@Column(name = "active", columnDefinition = "boolean default true")
	private Boolean active = true;

	@PrePersist
	protected void prePersist() {

		this.active = true;
	}
}
