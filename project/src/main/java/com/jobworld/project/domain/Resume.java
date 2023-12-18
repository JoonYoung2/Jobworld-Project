package com.jobworld.project.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name="jwResumeInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
	
	@Id @GeneratedValue
	@Column(name="resume_id")
	private Long id;
	
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Apply> applyList = new ArrayList<>(); 
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="member_id")
	private Member member;
	
	@Column(name="userImg")
	private String img;
	
	@Column(name="resumeTitle")
	private String title;

	@Builder
	Resume(Member member, String title, String img) {
		this.member = member;
		this.title = title;
		this.img = img;
	}

	public void updateTitle(String title){this.title=title;}

	public void updateImg(String img){this.img=img;}
}