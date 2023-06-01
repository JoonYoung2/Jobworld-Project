package com.jobworld.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplyDTO {
	private Long apply_id;
	private String resume_id;
	private String recruit_id;
	private int apply_state;
}
