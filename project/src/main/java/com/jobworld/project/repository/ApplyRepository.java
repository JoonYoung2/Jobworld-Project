package com.jobworld.project.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Apply;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
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
	
	public List<Apply> applyByRecruitId(Long id){
		return em.createQuery("select r from Apply r where r.recruit.id = :recruit_id", Apply.class)
				.setParameter("recruit_id", id)
				.getResultList();
	}
	
	public List<Apply> applyByResumeId(int id){
		return em.createQuery("select r from Apply r where r.resume.id = :resume_id", Apply.class)
				.setParameter("resume_id", id)
				.getResultList();
	}
	
	public void applyCancel(Long apply_id) {
		System.out.println("지원취소하기이이이이");
		Apply apply = em.find(Apply.class, apply_id);
		em.remove(apply);
	}
	
	public Apply findById(Long apply_id) {
		return em.createQuery("select r from Apply r where r.id = :apply_id", Apply.class)
				.setParameter("apply_id", apply_id)
				.getSingleResult();
	}
}