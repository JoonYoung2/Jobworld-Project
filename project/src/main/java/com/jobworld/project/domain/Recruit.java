package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.request.company.recruit.RecruitRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name="jwRecruitInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {
	
	@Id @GeneratedValue
	@Column(name="recruitId")
	private Long id;
	
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="compId")
	private Company company;

	@OneToMany(mappedBy="recruit")
	private List<Apply> applyList = new ArrayList<>();

	@Builder
	Recruit(RecruitRequestDto recruitRequestDto, Company company){
		this.title = recruitRequestDto.getRecruitTitle();
		this.career = recruitRequestDto.getRecruitCareer();
		this.education = recruitRequestDto.getRecruitEducation();
		this.employment = recruitRequestDto.getRecruitEmployment();
		this.salary = recruitRequestDto.getRecruitSalary();
		this.area = recruitRequestDto.getRecruitArea();
		this.time = recruitRequestDto.getRecruitTime();
		this.startDate = recruitRequestDto.getRecruitStartDate();
		this.endDate = recruitRequestDto.getRecruitEndDate();
		this.openType = recruitRequestDto.getRecruitOpenType();
		this.company = company;
	}

	public void updateTitle(String title){this.title = title;}
	public void updateCareer(String career){this.career = career;}
	public void updateEducation(String education){this.education = education;}
	public void updateEmployment(String employment){this.employment = employment;}
	public void updateSalary(String salary){this.salary = salary;}
	public void updateArea(String area){this.area = area;}
	public void updateTime(String time){this.time = time;}
	public void updateStartDate(String startDate){this.startDate = startDate;}
	public void updateEndDate(String endDate){this.endDate = endDate;}
	public void updateOpenType(int openType){this.openType = openType;}
}
