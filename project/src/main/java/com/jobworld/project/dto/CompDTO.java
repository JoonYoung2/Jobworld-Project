package com.jobworld.project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter	@Setter
public class CompDTO {
	@NotEmpty
	@Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
	private String comp_id;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
	private String comp_pw;
	@NotEmpty
	private String comp_pwCheck;
	@NotEmpty
	private String comp_nm;
	@NotEmpty
	private String comp_business_type;
	@Min(1)
	private int comp_empl_num;
	@NotEmpty
	private String comp_size;
	@NotEmpty
	private String comp_site;
	@NotEmpty
	private String comp_brand_img;
}
