package com.jobworld.project.dto.response.company.recruit;

import com.jobworld.project.domain.Recruit;
import lombok.Getter;

@Getter
public class RecruitResponseDto {
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

	public static RecruitResponseDto fromEntity(Recruit recruit){
		RecruitResponseDto recruitResponseDto = new RecruitResponseDto();
		recruitResponseDto.recruitId = recruit.getId();
		recruitResponseDto.compId = recruit.getCompany().getId();
		recruitResponseDto.recruitTitle = recruit.getTitle();
		recruitResponseDto.recruitCareer = recruit.getCareer();
		recruitResponseDto.recruitEducation = recruit.getEducation();
		recruitResponseDto.recruitEmployment = recruit.getEmployment();
		recruitResponseDto.recruitSalary = recruit.getSalary();
		recruitResponseDto.recruitArea = recruit.getArea();
		recruitResponseDto.recruitTime = recruit.getTime();
		recruitResponseDto.recruitStartDate = recruit.getStartDate();
		recruitResponseDto.recruitEndDate = recruit.getEndDate();
		recruitResponseDto.recruitOpenType = recruit.getOpenType();
		return recruitResponseDto;
	}
}
