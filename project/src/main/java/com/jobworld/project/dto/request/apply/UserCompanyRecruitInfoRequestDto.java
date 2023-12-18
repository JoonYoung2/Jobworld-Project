package com.jobworld.project.dto.request.apply;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserCompanyRecruitInfoRequestDto {
	private Long recruitId;
	private String compId;
	private String recruitTitle;
	private String recruitCareer;
	private String recruitEducation;
	private String recruitEmployment;
	private String recruitSalary;
	private String recruitArea;
	private String recruitTime;
	private String recruitStartDate;
	private String recruitEndDate;
	private int recruitOpenType;
	private String compNm;
	private String compBusinessType;
	private int compEmplNum;
	private String compSize;
	private String compSite;
	private String compBrandImg;
}
