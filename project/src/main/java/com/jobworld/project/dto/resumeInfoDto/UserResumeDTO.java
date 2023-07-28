package com.jobworld.project.dto.resumeInfoDto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserResumeDTO {
	private int resume_id;
	private String user_id;
	private String user_img;
	private String resume_title;
	private String user_birthday;
	private String user_email;
	private String user_nm;
	private String user_phone_num;
	private String zip_cd;
	private String address_info;
	private String address_detail;
	private MultipartFile file;
}
