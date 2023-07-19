package com.jobworld.project.dto.applyViewDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserCompanyRecruitInfoDTO {
	private Long recruit_id;
	private String comp_id;
	private String recruit_title;
	private String recruit_career;
	private String recruit_education;
	private String recruit_employment;
	private String recruit_salary;
	private String recruit_area;
	private String recruit_time;
	private String recruit_start_date;
	private String recruit_end_date;
	private int recruit_open_type;
	private String comp_nm;
	private String comp_business_type;
	private int comp_empl_num;
	private String comp_size;
	private String comp_site;
	private String comp_brand_img;
}
