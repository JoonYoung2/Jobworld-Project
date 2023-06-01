package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwCompanyInfo")
public class Company {
	
	@Id
	@Column(name="compId")
	private String id;
	
	@OneToMany(mappedBy = "company")
    private List<Recruit> recruit = new ArrayList<>();
	
	@Column(name="compPw")
	private String pw;
	
	@Column(name="compNm")
	private String name;
	
	@Column(name="compBusinessType")
	private String businessType;
	
	@Column(name="compEmplNum")
	private int emplNum;
	
	@Column(name="compSize")
	private String size;
	
	@Column(name="corpSite")
	private String site;
}
