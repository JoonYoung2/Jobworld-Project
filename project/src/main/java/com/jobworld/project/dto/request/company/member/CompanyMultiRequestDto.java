package com.jobworld.project.dto.request.company.member;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompanyMultiRequestDto {
	@NotEmpty
	@Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
	private String compId;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
	private String compPw;
	@NotEmpty
	private String compPwCheck;
	@NotEmpty
	private String compNm;
	@NotEmpty
	private String compBusinessType;
	@Min(1)
	private int compEmplNum;
	@NotEmpty
	private String compSize;
	@NotEmpty
	private String compSite;
	@NotEmpty
	private MultipartFile file;
}
