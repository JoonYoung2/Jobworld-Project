package com.jobworld.project.dto.response.member;

import com.jobworld.project.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String userId;
    private String userPw;
    private String userBirthday;
    private String userEmail;
    private String userNm;
    private String userPhoneNum;
    private String zipCd;
    private String addressInfo;
    private String addressDetail;
    private int loginType;

    public static MemberResponseDto fromEntity(Member member){
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.userId = member.getId();
        memberResponseDto.userPw = member.getPw();
        memberResponseDto.userBirthday = member.getBirthday();
        memberResponseDto.userEmail = member.getEmail();
        memberResponseDto.userNm = member.getName();
        memberResponseDto.userPhoneNum = member.getPhoneNum();
        memberResponseDto.zipCd = member.getZipCd();
        memberResponseDto.addressInfo = member.getAddressInfo();
        memberResponseDto.addressDetail = member.getAddressDetail();
        memberResponseDto.loginType = member.getLoginType();
        return memberResponseDto;
    }
}
