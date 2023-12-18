package com.jobworld.project.dto.response.apply;

import com.jobworld.project.domain.Recruit;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRecruitViewResponseDto {
	private Long recruitId;
	private String compId;
	private String recruitTitle;
	private String compBrandImg;
	private String compNm;

	@Builder
	public UserRecruitViewResponseDto fromEntity(Recruit recruit){
		UserRecruitViewResponseDto userRecruitViewResponseDto = new UserRecruitViewResponseDto();
		userRecruitViewResponseDto.recruitId = recruit.getId();
		userRecruitViewResponseDto.compId = recruit.getCompany().getId();
		userRecruitViewResponseDto.recruitTitle = recruit.getTitle();
		userRecruitViewResponseDto.compBrandImg = recruit.getCompany().getBrandImg();
		userRecruitViewResponseDto.compNm = recruit.getCompany().getName();
		return userRecruitViewResponseDto;
	}
}
