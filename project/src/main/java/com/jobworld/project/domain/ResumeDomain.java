package com.jobworld.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwResumeInfo")
public class ResumeDomain {
	@Id
	@Column(name="user_id")
	private String id;
	
	@Column(name="user_img")
	private String img;
	
	@Column(name="user_title")
	private String title;
}
