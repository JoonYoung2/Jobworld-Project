package com.jobworld.project.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Company;
import com.jobworld.project.domain.Recruit;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchRepository {
	
	private final EntityManager em;
	
	
	public List<Recruit> getRecruitSearchInfo(String userSearch) {
		System.out.println("search content --> "+userSearch);
		userSearch = "%" + userSearch + "%";
		List<Recruit> list = em.createQuery("select r from Recruit r where r.title like :search", Recruit.class)
				.setParameter("search", userSearch)
				.getResultList();
		
		System.out.println("list size() ===> "+list.size());
		return list;
	}
	
	public List<Recruit> getRecruitSearchInfo(String companySearch, String compId) {
		companySearch = "%" + companySearch + "%";
		List<Recruit> list = em.createQuery("select r from Recruit r where r.title like :search AND r.company.id = :compId", Recruit.class)
				.setParameter("search", companySearch)
				.setParameter("compId", compId)
				.getResultList();
		
		System.out.println("list size() ===> "+list.size());
		return list;
	}
	
	public Company getCompanyById(String id) {
		Company comp = em.find(Company.class, id);
		return comp;
	}
	
}
