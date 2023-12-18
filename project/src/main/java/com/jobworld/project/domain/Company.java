package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.request.company.member.CompanyMemberRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name="jwCompanyInfo")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
	
	@Id
	@Column(name="compId")
	private String id;
	
	@Column(name="compPw")
	private String pw;
	
	@Column(name="compNm")
	private String name;
	
	@Column(name="compBusinessType")
	private String businessType;
	
	@Column(name="compEmplNum")
	private int emplNum;
	
	@Column(name="compSize")
	private String size;
	
	@Column(name="compSite")
	private String site;
	
	@Column(name="compBrandImg")
	private String brandImg;

	@OneToMany(mappedBy = "company")
	private List<Recruit> recruit = new ArrayList<>();

	@Builder
	Company(CompanyMemberRequestDto companyMemberRequestDto){
		this.id = companyMemberRequestDto.getCompId();
		this.pw = companyMemberRequestDto.getCompPw();
		this.name = companyMemberRequestDto.getCompNm();
		this.businessType = companyMemberRequestDto.getCompBusinessType();
		this.emplNum = companyMemberRequestDto.getCompEmplNum();
		this.size = companyMemberRequestDto.getCompSize();
		this.site = companyMemberRequestDto.getCompSite();
		this.brandImg = companyMemberRequestDto.getCompBrandImg();
	}

	public void updateName(String name){this.name=name;}
	public void updateBusinessType(String businessType){this.businessType=businessType;}
	public void updateEmplNum(int emplNum){this.emplNum=emplNum;}
	public void updateSize(String size){this.size=size;}
	public void updateSite(String site){this.site=site;}
	public void updateBrandImg(String brandImg){this.brandImg=brandImg;}
}
