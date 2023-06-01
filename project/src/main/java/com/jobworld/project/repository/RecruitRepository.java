package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RecruitRepository {
	
	private final EntityManager em;
	
	public void save(Recruit recruit) {
		try {
			em.persist(recruit);
		}catch(Exception e) {
			log.error("RecruitRepository save(RecruitDomain) error --> {}", e);
		}
	}
	
	public void update(Recruit recruit) throws Exception {
		Recruit find;
		try {
			find = findOne(recruit.getId());
			
			find.setCarrer(recruit.getCarrer());
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
	
	public Recruit findOne(Long id) throws Exception {
		Recruit find = null;
		
		try {
			find = em.find(Recruit.class, id);
		}catch(Exception e) {
			log.error("RecruitRepository findOne(Long) error --> {}", e);
		}
        return find;
    }

    public List<Recruit> recruitFindAll(String corp_id) throws Exception {
    	List<Recruit> list = null;
    	
    	try {
    		list = em.createQuery("select m from Member m where m.corp_id = :corp_id", Recruit.class)
            		.setParameter("user_id", corp_id)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("RecruitRepository recruitFindAll(String) error --> {}", e);
    	}
        return list;
    }
}
