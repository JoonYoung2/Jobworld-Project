package com.jobworld.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplyDTO {
	private Long apply_id;
	private int resume_id;
	private Long recruit_id;
	private int apply_state;
}
