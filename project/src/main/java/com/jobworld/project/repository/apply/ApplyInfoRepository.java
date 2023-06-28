package com.jobworld.project.repository.apply;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Apply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplyInfoRepository {
	private final EntityManager em;
	
	public List<UserApplyStatusDTO> ApplyUserInfo(int resume_id, Long recruit_id){
		return em.createQuery("select new com.jobworld.project.repository.apply.UserApplyStatusDTO" +
				"(r.member.id, r.member.birthday, r.member.email, r.member.name, r.img, r.title)" +
				" from Apply a" +
				" join a.resume r" +
				" where a.resume.id = :resume_id and a.recruit.id = :recruit_id", UserApplyStatusDTO.class)
		.setParameter("resume_id", resume_id)
		.setParameter("recruit_id", recruit_id)
		.getResultList();
	}
}
