package com.jobworld.project.dto.response.search;

import com.jobworld.project.domain.Recruit;
import lombok.Getter;

@Getter
public class recruitViewResponseDto {
	private Long recruitId;
	private String compId;
	private String recruitTitle;
	private String compBrandImg;
	private String compNm;

	public static recruitViewResponseDto fromEntity(Recruit recruit){
		recruitViewResponseDto userRecruitViewResponseDto = new recruitViewResponseDto();
		userRecruitViewResponseDto.recruitId = recruit.getId();
		userRecruitViewResponseDto.compId = recruit.getCompany().getId();
		userRecruitViewResponseDto.recruitTitle = recruit.getTitle();
		userRecruitViewResponseDto.compBrandImg = recruit.getCompany().getBrandImg();
		userRecruitViewResponseDto.compNm = recruit.getCompany().getName();
		return userRecruitViewResponseDto;
	}
}
