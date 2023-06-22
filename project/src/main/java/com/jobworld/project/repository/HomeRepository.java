package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.dto.RecruitDTO;

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
}
