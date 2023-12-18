package com.jobworld.project.repository.apply;

import lombok.Data;

@Data
public class CompApplyStatusDTO {
	private String compId;
	private Long applyId;
	private String compNm;
	private String compBusinessType;
	private int compEmplNum;
	private String compSize;
	private String compSite;
	private Long recruitId;
	private String recruitCareer;
	private String recruitEducation;
	private String recruitEmployment;
	private String recruitSalary;
	private String recruitArea;
	private String recruitTime;
	private String recruitStartDate;
	private String recruitEndDate;
	private int recruitOpenType;
	private int state;
	private String recruitTitle;
	private String compBrandImg;
}
