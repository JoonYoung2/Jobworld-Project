package com.jobworld.project.dto.applyViewDto;

import lombok.Data;

@Data
public class UserResumeRecruitDTO {
	private Long recruit_id;
	private int resume_id;
	private String resume_title;
	private String user_email;
	private String user_nm;
	private String user_phone_num;
	private String recruit_title;
	private String comp_nm;
}
