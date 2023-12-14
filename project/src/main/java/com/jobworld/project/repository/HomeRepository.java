package com.jobworld.project.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeRepository {
	private final EntityManager em;

	public List<Recruit> getRecruitInfo() {
		
		List<Recruit> list = em.createQuery("select r from Recruit r", Recruit.class)
				.getResultList();
		
		return list;
	}

	public List<Recruit> findByCompId(String comp_id) {
		List<Recruit> list = em.createQuery("select r from Recruit r where r.company.id=:comp_id", Recruit.class)
				.setParameter("comp_id", comp_id)
				.getResultList();
		return list;
	}
}
