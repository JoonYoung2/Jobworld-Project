package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.jobworld.project.dto.ApplyDTO;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwApplyInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {
	@Id
	@GeneratedValue
	@Column(name="applyId")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="resumeId")
	private Resume resume;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recruitId")
	private Recruit recruit;
	
	@Column(name="applyState")
	private int state;
	
	public Apply setApply(ApplyDTO dto) {
		Apply apply = new Apply();
		return apply;
	}
	
	public static Apply setApply(Resume resume, Recruit recruit) {
		Apply apply = new Apply();
		apply.setResume(resume);
		apply.setRecruit(recruit);
		apply.setState(0);
		
		return apply;
	}
	
	public static List<ApplyDTO> setApplyDto(List<Apply> apply){
		List<ApplyDTO> list = new ArrayList<>();
		for(int i = 0; i < apply.size(); ++i) {
			ApplyDTO dto = new ApplyDTO();
			
			dto.setApply_id(apply.get(i).getId());
			dto.setRecruit_id(apply.get(i).getRecruit().getId());
			dto.setResume_id(apply.get(i).getResume().getId());
			dto.setApply_state(apply.get(i).getState());
			list.add(dto);
		}
		
		return list;
	}
}
