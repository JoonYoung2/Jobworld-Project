package com.jobworld.project.dto.response.user.resume;

import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.response.user.member.MemberResponseDto;
import lombok.Getter;

@Getter
public class ResumeResponseDto {
    private Long resumeId;
    private String userImg;
    private String resumeTitle;
    private MemberResponseDto memberResponseDto;

    public static ResumeResponseDto fromEntity(Resume resume){
        ResumeResponseDto resumeResponseDto = new ResumeResponseDto();
        resumeResponseDto.resumeId = resume.getId();
        resumeResponseDto.userImg = resume.getImg();
        resumeResponseDto.resumeTitle = resume.getTitle();
        resumeResponseDto.memberResponseDto = MemberResponseDto.fromEntity(resume.getMember());
        return resumeResponseDto;
    }
}
