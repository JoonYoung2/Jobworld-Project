package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwResumeInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeDomain {
	
	@Id @GeneratedValue
	@Column(name="resume_id")
	private int id;
	
	@OneToMany(mappedBy = "resumeDomain")
	private List<ApplyDomain> applyList = new ArrayList<>(); 
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private UserDomain userDomain;
	
	@Column(name="user_img")
	private String img;
	
	@Column(name="resume_title")
	private String title;
}