package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Apply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplyRepository {
	
	private final EntityManager em;
	
	public void save(Apply apply) {
		em.persist(apply);
	}

	public List<Apply> checkApplyUser(Long recruit_id, int resume_id) {
		TypedQuery<Apply> query = em.createQuery("select a from Apply a where a.recruit.id = :recruit_id and a.resume.id = :resume_id", Apply.class)
				.setParameter("recruit_id", recruit_id)
				.setParameter("resume_id", resume_id);
		List<Apply> list = query.getResultList();

		return list;
	}

	public List<Apply> getApplyInfo(Long recruit_id) {
		return em.createQuery("select a from Apply a where a.recruit.id = :recruit_id", Apply.class)
				.setParameter("recruit_id", recruit_id)
				.getResultList();
	}

	public List<Apply> getApplyInfo(int resume_id) {
		return em.createQuery("select a from Apply a where a.resume.id = :resume_id", Apply.class)
				.setParameter("resume_id", resume_id)
				.getResultList();
	}

	public void applyCancel(Long apply_id) {
		Apply apply = em.find(Apply.class, apply_id);
		em.remove(apply);
	}

	public Apply find(Long apply_id) {
		return em.find(Apply.class, apply_id);
	}
}
