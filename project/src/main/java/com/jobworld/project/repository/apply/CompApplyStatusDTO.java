package com.jobworld.project.repository.apply;

import lombok.Data;

@Data
public class CompApplyStatusDTO {
	private String comp_id;
	private Long apply_id;
	private String comp_nm;
	private String comp_business_type;
	private int comp_empl_num;
	private String comp_size;
	private String comp_site;
	private Long recruit_id;
	private String recruit_career;
	private String recruit_education;
	private String recruit_employment;
	private String recruit_salary;
	private String recruit_area;
	private String recruit_time;
	private String recruit_start_date;
	private String recruit_end_date;
	private int recruit_open_type;
	private int state;
}
