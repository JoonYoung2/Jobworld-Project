package com.jobworld.project.dto.response.apply;

import com.jobworld.project.domain.Apply;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyResponseDto {
	private Long applyId;
	private Long resumeId;
	private Long recruitId;
	private int applyState;

	public static ApplyResponseDto fromEntity(Apply apply){
		ApplyResponseDto applyResponseDto = new ApplyResponseDto();
		applyResponseDto.applyId = apply.getId();
		applyResponseDto.resumeId = apply.getResume().getId();
		applyResponseDto.recruitId = apply.getRecruit().getId();
		applyResponseDto.applyState = apply.getState();
		return applyResponseDto;
	}
}
