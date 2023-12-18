package com.jobworld.project.dto.response.apply;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResumeRecruitResponseDto {
	private Long recruitId;
	private Long resumeId;
	private String resumeTitle;
	private String userEmail;
	private String userNm;
	private String userPhoneNum;
	private String recruitTitle;
	private String compNm;

	@Builder
	public UserResumeRecruitResponseDto fromEntity(Recruit recruit, Resume resume){
		UserResumeRecruitResponseDto userResumeRecruitResponseDto = new UserResumeRecruitResponseDto();
		userResumeRecruitResponseDto.recruitId = recruit.getId();
		userResumeRecruitResponseDto.resumeId = resume.getId();
		userResumeRecruitResponseDto.resumeTitle = resume.getTitle();
		userResumeRecruitResponseDto.userEmail = resume.getMember().getEmail();
		userResumeRecruitResponseDto.userNm = resume.getMember().getName();
		userResumeRecruitResponseDto.userPhoneNum = resume.getMember().getPhoneNum();
		userResumeRecruitResponseDto.recruitTitle = recruit.getTitle();
		userResumeRecruitResponseDto.compNm = recruit.getCompany().getName();
		return userResumeRecruitResponseDto;
	}
}
