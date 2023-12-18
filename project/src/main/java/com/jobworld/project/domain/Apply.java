package com.jobworld.project.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
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

	@Builder
	Apply(Resume resume, Recruit recruit){
		this.resume = resume;
		this.recruit = recruit;
		this.state = 0;
	}

	public void updateState(int state){this.state=state;}
}
