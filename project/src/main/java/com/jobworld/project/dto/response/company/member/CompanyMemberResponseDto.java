package com.jobworld.project.dto.response.company.member;

import com.jobworld.project.domain.Company;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CompanyMemberResponseDto {
    private String compId;
    private String compPw;
    private String compNm;
    private String compBusinessType;
    private int compEmplNum;
    private String compSize;
    private String compSite;
    private String compBrandImg;

    public static CompanyMemberResponseDto fromEntity(Company company){
        CompanyMemberResponseDto companyMemberResponseDto = new CompanyMemberResponseDto();
        companyMemberResponseDto.compId = company.getId();
        companyMemberResponseDto.compPw = company.getPw();
        companyMemberResponseDto.compNm = company.getName();
        companyMemberResponseDto.compBusinessType = company.getBusinessType();
        companyMemberResponseDto.compEmplNum = company.getEmplNum();
        companyMemberResponseDto.compSize = company.getSize();
        companyMemberResponseDto.compSite = company.getSite();
        companyMemberResponseDto.compBrandImg = company.getBrandImg();
        return companyMemberResponseDto;
    }
}
