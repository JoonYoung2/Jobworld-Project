package com.jobworld.project.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RecruitRepository {
	
	private final EntityManager em;
	
	public void save(Recruit recruit) {
		em.persist(recruit);
	}
	
	public void update(Recruit recruit) {
		Recruit find;
		try {
			find = findOne(recruit.getId());
			
			find.setCareer(recruit.getCareer());
			find.setEducation(recruit.getEducation());
			find.setEmployment(recruit.getEmployment());
			find.setSalary(recruit.getSalary());
			find.setArea(recruit.getArea());
			find.setTime(recruit.getTime());
			find.setStartDate(recruit.getStartDate());
			find.setEndDate(recruit.getEndDate());
			find.setOpenType(recruit.getOpenType());
		}catch(Exception e) {
			log.error("RecruitRepository update(RecruitDomain) error --> {}", e);
		}
	}
	
	public Recruit findOne(Long id) {
		Recruit find =  em.find(Recruit.class, id);
		
        return find;
    }

    public List<Recruit> recruitFindAll(String corp_id) {
    	List<Recruit> list = em.createQuery("select m from Member m where m.corp_id = :corp_id", Recruit.class)
            		.setParameter("user_id", corp_id)
            		.getResultList();
    	
        return list;
    }

	public List<Recruit> findByCompId(String comp_id) {
		
		return em.createQuery("select r from Recruit r where r.company.id = :comp_id", Recruit.class)
				.setParameter("comp_id", comp_id)
				.getResultList();
	}
}
