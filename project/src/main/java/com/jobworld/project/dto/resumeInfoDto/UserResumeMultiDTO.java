package com.jobworld.project.dto.resumeInfoDto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResumeMultiDTO {
	private int resume_id;
	private String user_id;
	private String user_img;
	private String resume_title;
	private MultipartFile file;
}
