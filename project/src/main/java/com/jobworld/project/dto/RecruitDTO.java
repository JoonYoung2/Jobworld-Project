package com.jobworld.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecruitDTO {
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
}
