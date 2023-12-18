package com.jobworld.project.repository.apply;

import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplyInfoRepository {
	private final EntityManager em;
	
	public List<UserApplyStatusDTO> ApplyUserInfo(int resumeId, Long recruitId){
		return em.createQuery("select new com.jobworld.project.repository.apply.UserApplyStatusDTO" +
				"(r.member.id, r.member.birthday, r.member.email, r.member.name, r.img, r.title)" +
				" from Apply a" +
				" join a.resume r" +
				" where a.resume.id = :resumeId and a.recruit.id = :recruitId", UserApplyStatusDTO.class)
		.setParameter("resumeId", resumeId)
		.setParameter("recruitId", recruitId)
		.getResultList();
	}
}
