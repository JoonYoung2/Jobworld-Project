package com.jobworld.project.dto.request.resume;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ResumeRequestDto {
	private Long resumeId;
	private String userId;
	private String resumeTitle;
	private int loginType;
	private MultipartFile file;
}
