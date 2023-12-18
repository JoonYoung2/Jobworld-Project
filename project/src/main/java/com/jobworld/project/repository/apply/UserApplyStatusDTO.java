package com.jobworld.project.repository.apply;

import lombok.Data;

@Data
public class UserApplyStatusDTO {
	private String userId;
	private Long applyId;
	private Long resumeId;
	private String userBirthday;
	private String userEmail;
	private String userNm;
	private String userImg;
	private String userPhoneNum;
	private String zipCd;
	private String addressInfo;
	private String addressDetail;
	private String resumeTitle;
	private int state;
}
