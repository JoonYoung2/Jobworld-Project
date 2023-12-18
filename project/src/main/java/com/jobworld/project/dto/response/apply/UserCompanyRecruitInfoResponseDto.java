package com.jobworld.project.dto.response.apply;

import com.jobworld.project.domain.Recruit;
import lombok.Getter;

@Getter
public class UserCompanyRecruitInfoResponseDto {
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

	public UserCompanyRecruitInfoResponseDto fromEntity(Recruit recruit){
		UserCompanyRecruitInfoResponseDto userCompanyRecruitInfoResponseDto = new UserCompanyRecruitInfoResponseDto();
		userCompanyRecruitInfoResponseDto.recruitId = recruit.getId();
		userCompanyRecruitInfoResponseDto.compId = recruit.getCompany().getId();
		userCompanyRecruitInfoResponseDto.recruitTitle = recruit.getTitle();
		userCompanyRecruitInfoResponseDto.recruitCareer = recruit.getCareer();
		userCompanyRecruitInfoResponseDto.recruitEducation = recruit.getEducation();
		userCompanyRecruitInfoResponseDto.recruitEmployment = recruit.getEmployment();
		userCompanyRecruitInfoResponseDto.recruitSalary = recruit.getSalary();
		userCompanyRecruitInfoResponseDto.recruitArea = recruit.getArea();
		userCompanyRecruitInfoResponseDto.recruitTime = recruit.getTime();
		userCompanyRecruitInfoResponseDto.recruitStartDate = recruit.getStartDate();
		userCompanyRecruitInfoResponseDto.recruitEndDate = recruit.getEndDate();
		userCompanyRecruitInfoResponseDto.recruitOpenType = recruit.getOpenType();
		userCompanyRecruitInfoResponseDto.compNm = recruit.getCompany().getName();
		userCompanyRecruitInfoResponseDto.compBusinessType = recruit.getCompany().getBusinessType();
		userCompanyRecruitInfoResponseDto.compEmplNum = recruit.getCompany().getEmplNum();
		userCompanyRecruitInfoResponseDto.compSize = recruit.getCompany().getSize();
		userCompanyRecruitInfoResponseDto.compSite = recruit.getCompany().getSite();
		userCompanyRecruitInfoResponseDto.compBrandImg = recruit.getCompany().getBrandImg();
		return userCompanyRecruitInfoResponseDto;
	}
}
