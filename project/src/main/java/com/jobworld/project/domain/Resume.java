package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwResumeInfo")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Resume {
	@Id @GeneratedValue
	@Column(name="resumeId")
	private int id;
	
	@OneToMany(mappedBy = "resume")
	private List<Apply> applyList = new ArrayList<>(); 
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private Member member;
	
	@Column(name="userImg")
	private String img;
	
	@Column(name="resumeTitle")
	private String title;
	
	// --연관관계 메서드--
	
	public void setMember(Member member) {
		this.member = member;
		member.setResume(this);
	}
	public void addApplyList(Apply applyList) {
		this.applyList.add(applyList);
		applyList.setResume(this);
	}
}