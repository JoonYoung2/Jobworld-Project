package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchRepository {
	
	private final EntityManager em;
	
	
	public List<Recruit> getRecruitSearchInfo(String userSearch) {
		List<Recruit> list = em.createQuery("select r from Recruit r where ", Recruit.class)
				.setParameter("search", userSearch)
				.getResultList();
		return null;
	}
	
}
