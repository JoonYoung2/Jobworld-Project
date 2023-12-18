package com.jobworld.project.dto.request.company;

import lombok.Data;

@Data
public class CompanyIndexViewDto {
	private String compId;
	private String compNm;
	private String compBrandImg;
	private Long recruitId;
	private String recruitTitle;
}