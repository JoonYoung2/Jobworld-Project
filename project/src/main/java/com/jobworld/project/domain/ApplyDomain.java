package com.jobworld.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwApplyInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyDomain {
	@Id @GeneratedValue
	@Column(name="applyId")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="resume_id")
	private ResumeDomain resumeDomain;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="recruit_id")
	private RecruitDomain recruitDomain;
	
	@Column(name="applyState")
	private int state;
}
