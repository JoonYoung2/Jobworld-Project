package com.jobworld.project.repository.apply;

import lombok.Data;

@Data
public class UserApplyStatusDTO {
	private String user_id;
	private Long apply_id;
	private String user_birthday;
	private String user_email;
	private String user_nm;
	private String user_img;
	private String resume_title;
	private int state;
}
