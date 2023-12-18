package com.jobworld.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import com.jobworld.project.dto.request.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "jwUserInfo")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@Column(name = "userId")
	private String id;

	@Column(name = "userPw")
	private String pw;

	@Column(name = "userNm")
	private String name;

	@Column(name = "userBirthday")
	private String birthday;

	@Column(name = "userEmail")
	private String email;

	@Column(name = "userPhoneNum")
	private String phoneNum;

	private String zipCd;

	private String addressInfo;

	private String addressDetail;

	private int loginType;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
	private Resume resume;

	@Builder
	Member (MemberRequestDto memberRequestDto) {
		this.id = memberRequestDto.getUserId();
		this.pw = memberRequestDto.getUserPw();
		this.name = memberRequestDto.getUserNm();
		this.birthday = memberRequestDto.getUserBirthday();
		this.email = memberRequestDto.getUserEmail();
		this.phoneNum = memberRequestDto.getUserPhoneNum();
		this.zipCd = memberRequestDto.getZipCd();
		this.addressInfo = memberRequestDto.getAddressInfo();
		this.addressDetail = memberRequestDto.getAddressDetail();
		this.loginType = memberRequestDto.getLoginType();
	}

	public void updateName(String name){this.name=name;}
	public void updateBirthday(String birthday){this.birthday=birthday;}
	public void updateEmail(String email){this.email=email;}
	public void updatePhoneNum(String phoneNum){this.phoneNum=phoneNum;}
	public void updateZipCd(String zipCd){this.zipCd=zipCd;}
	public void updateAddressInfo(String addressInfo){this.addressInfo=addressInfo;}
	public void updateAddressDetail(String addressDetail){this.addressDetail=addressDetail;}
}