package com.jobworld.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.jobworld.project.dto.MemberDTO;

@Entity
@Table(name = "jwUserInfo")
@Getter @Setter
public class Member {

	@Id
	@Column(name = "userId")
	private String id;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
	private Resume resume;

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

	private String zip_cd;

	private String address_info;

	private String address_detail;

	private int login_type;

	public static Member setMember(MemberDTO dto) {
		Member member = new Member();
		member.setId(dto.getUser_id());
		member.setPw(dto.getUser_pw());
		member.setName(dto.getUser_nm());
		member.setBirthday(dto.getUser_birthday());
		member.setEmail(dto.getUser_email());
		member.setPhoneNum(dto.getUser_phone_num());
		member.setZip_cd(dto.getZip_cd());
		member.setAddress_info(dto.getAddress_info());
		member.setAddress_detail(dto.getAddress_detail());
		member.setLogin_type(0);
		
		return member;
	}
}