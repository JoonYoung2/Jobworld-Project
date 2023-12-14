package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="jwResumeInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
	
	@Id @GeneratedValue
	@Column(name="resumeId")
	private int id;
	
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
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
	
	// -- 생성 메서드 --
	
	public static Resume createResume(Member member, String user_id, String user_img, String resume_title) {
		Resume resume = new Resume();
		resume.setMember(member);
		resume.setImg(user_img);
		resume.setTitle(resume_title);
		return resume;
	}
}