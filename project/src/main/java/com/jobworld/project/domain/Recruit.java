package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.RecruitDTO;

import jakarta.persistence.*;
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
	
	@Column(name="recruitTitle")
	private String title;
	
	@Column(name="recruitCareer")
	private String career;
	
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

	public static Recruit setRecruit(RecruitDTO dto, Company comp) {
		Recruit recruit = new Recruit();
		recruit.setCompany(comp);
		recruit.setTitle(dto.getRecruit_title());
		recruit.setCareer(dto.getRecruit_career());
		recruit.setEducation(dto.getRecruit_education());
		recruit.setEmployment(dto.getRecruit_employment());
		recruit.setSalary(dto.getRecruit_salary());
		recruit.setArea(dto.getRecruit_area());
		recruit.setTime(dto.getRecruit_time());
		recruit.setStartDate("0");
		recruit.setEndDate("0");
		recruit.setOpenType(0);
		return recruit;
	}
	
}
