package com.jobworld.project.dto.request.apply;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResumeRecruitRequestDto {
	private Long recruitId;
	private Long resumeId;
	private String resumeTitle;
	private String userEmail;
	private String userNm;
	private String userPhoneNum;
	private String recruitTitle;
	private String compNm;
}
