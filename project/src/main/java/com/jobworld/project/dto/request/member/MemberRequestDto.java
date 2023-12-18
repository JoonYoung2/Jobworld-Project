package com.jobworld.project.dto.request.member;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberRequestDto {
	@Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
	private String userId;
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
	private String userPw;
	private String userPwCheck;
	@Min(value=10000000)
	private String userBirthday;
	@Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")
	private String userEmail;
	private String userNm;
	@Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$")
	private String userPhoneNum;
	private String zipCd;
	private String addressInfo;
	private String addressDetail;
	private int loginType;
}
