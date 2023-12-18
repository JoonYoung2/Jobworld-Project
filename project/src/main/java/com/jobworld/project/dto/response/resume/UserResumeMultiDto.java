package com.jobworld.project.dto.response.resume;

import com.jobworld.project.domain.Resume;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserResumeMultiDto {
	private int resumeId;
	private String userId;
	private String userImg;
	private String resumeTitle;
	private MultipartFile file;
}
