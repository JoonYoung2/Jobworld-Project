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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwRecruitInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {
	
	@Id @GeneratedValue
	@Column(name="recruitId")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="compId")
	private Company company;
	
	@OneToMany(mappedBy="recruit")
	private List<Apply> applyList = new ArrayList<>();
	
	@Column(name="recruitCarrer")
	private String carrer;
	
	@Column(name="recruitEducation")
	private String education;
	
	@Column(name="recruitEmployment")
	private String employment;
	
	@Column(name="recruitSalary")
	private String salary;
	
	@Column(name="recruitArea")
	private String area;
	
	@Column(name="recruitTime")
	private String time;
	
	@Column(name="recruitStartDate")
	private String startDate;
	
	@Column(name="recruitEndDate")
	private String endDate;
	
	@Column(name="recruitOpenType")
	private int openType;
	
	// -- 연관관계 메서드 --
	public void setCompany(Company company) {
		this.company = company;
		company.getRecruit().add(this);
	}
	
	public void addApplyList(Apply applyList) {
		this.applyList.add(applyList);
		applyList.setRecruit(this);
	}
	
}
