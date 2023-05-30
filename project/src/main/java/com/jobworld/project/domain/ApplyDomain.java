package com.jobworld.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwApplyInfo")
public class ApplyDomain {
	@Id @GeneratedValue
	private Long apply_id;
	
	private String user_id;
	
	private String recruit_id;
	
	@Column(name="applyState")
	private int state;
}
