package com.jobworld.project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	@Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
	private String user_id;
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
	private String user_pw;
	private String user_pwCheck;
	@Min(value=10000000)
	private String user_birthday;
	@Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")
	private String user_email;
	private String user_nm;
	@Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$")
	private String user_phone_num;
	private String zip_cd;
	private String address_info;
	private String address_detail;
	private int login_type;
}
