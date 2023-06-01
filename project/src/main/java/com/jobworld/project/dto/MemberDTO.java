package com.jobworld.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	private String user_id;
	private String user_pw;
	private String user_birthday;
	private String user_email;
	private String user_nm;
	private String user_phone_num;
	private String zip_cd;
	private String address_info;
	private String address_detail;
	private int login_type;
}
