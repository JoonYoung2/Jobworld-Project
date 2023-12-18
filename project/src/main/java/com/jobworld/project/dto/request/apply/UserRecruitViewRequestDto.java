package com.jobworld.project.dto.request.apply;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRecruitViewRequestDto {
	private Long recruitId;
	private String compId;
	private String recruitTitle;
	private String compBrandImg;
	private String compNm;
}
