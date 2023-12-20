package com.jobworld.project.dto.response.user.resume;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class UserResumeMultiResponseDto {
	private int resumeId;
	private String userId;
	private String userImg;
	private String resumeTitle;
}
