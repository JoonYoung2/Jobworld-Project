package com.jobworld.project.repository.apply;

import lombok.Data;

@Data
public class UserApplyStatusDTO {
	private String user_id;
	private Long apply_id;
	private int resume_id;
	private String user_birthday;
	private String user_email;
	private String user_nm;
	private String user_img;
	private String user_phone_num;
	private String zip_cd;
	private String address_info;
	private String address_detail;
	private String resume_title;
	private int state;
}
