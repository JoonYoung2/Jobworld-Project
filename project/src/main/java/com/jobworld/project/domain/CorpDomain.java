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
@Table(name="jwCorpInfo")
public class CorpDomain {
	
	@Id
	@Column(name="corpId")
	private String id;
	
	@OneToMany(mappedBy = "corpDomain")
    private List<RecruitDomain> recruitDomain = new ArrayList<>();
	
	@Column(name="corpPw")
	private String pw;
	
	@Column(name="corpNm")
	private String name;
	
	@Column(name="corpBusinessType")
	private String businessType;
	
	@Column(name="corpEmplNum")
	private int emplNum;
	
	@Column(name="corpSize")
	private String size;
	
	@Column(name="corpSite")
	private String site;
}
