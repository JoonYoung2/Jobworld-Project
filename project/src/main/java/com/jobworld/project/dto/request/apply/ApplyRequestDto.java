package com.jobworld.project.dto.request.apply;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplyRequestDto {
	private Long applyId;
	private Long resumeId;
	private Long recruitId;
	private int applyState;
}
