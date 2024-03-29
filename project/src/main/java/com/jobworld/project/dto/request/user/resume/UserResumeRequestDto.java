package com.jobworld.project.dto.request.user.resume;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserResumeRequestDto {
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
	private MultipartFile file;
}
