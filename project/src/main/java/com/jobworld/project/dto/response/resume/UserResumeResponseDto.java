package com.jobworld.project.dto.response.resume;

import com.jobworld.project.domain.Resume;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Getter
public class UserResumeResponseDto {
	private Long resumeId;
	private String userId;
	private String userImg;
	private String resumeTitle;
	private String userBirthday;
	private String userEmail;
	private String userNm;
	private String userPhoneNum;
	private String zipCd;
	private String addressInfo;
	private String addressDetail;

	public static UserResumeResponseDto fromEntity(Resume resume){
		UserResumeResponseDto userResumeResponseDto = new UserResumeResponseDto();
		userResumeResponseDto.resumeId = resume.getId();
		userResumeResponseDto.userId = resume.getMember().getId();
		userResumeResponseDto.userImg = resume.getImg();
		userResumeResponseDto.resumeTitle = resume.getTitle();
		userResumeResponseDto.userBirthday = resume.getMember().getBirthday();
		userResumeResponseDto.userEmail = resume.getMember().getEmail();
		userResumeResponseDto.userNm = resume.getMember().getName();
		userResumeResponseDto.userPhoneNum = resume.getMember().getPhoneNum();
		userResumeResponseDto.zipCd = resume.getMember().getZipCd();
		userResumeResponseDto.addressInfo = resume.getMember().getAddressInfo();
		userResumeResponseDto.addressDetail = resume.getMember().getAddressDetail();
		return userResumeResponseDto;
	}
}
