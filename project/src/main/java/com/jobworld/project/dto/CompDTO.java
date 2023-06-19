package com.jobworld.project.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompDTO {
	@Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
	private String comp_id;
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
	private String comp_pw;
	private String comp_pwCheck;
	private String comp_nm;
	private String comp_business_type;
	private int comp_empl_num;
	private String comp_size;
	private String comp_site;
}
