package com.jobworld.project.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CompRepository {
	private final EntityManager em;
	
	public void save(Company comp) {
		try {
			em.persist(comp);
		}catch(Exception e) {
			log.error("CorpRepository save(CorpDomain) error --> {}", e);
		}
	}
	
	public void update(Company comp) {
		Company find = new Company();
		try {
			find = findOne(comp.getId());
			find.setPw(comp.getPw());
			find.setName(comp.getName());
			find.setBusinessType(comp.getBusinessType());
			find.setEmplNum(comp.getEmplNum());
			find.setSize(comp.getSize());
			find.setSite(comp.getSite());
			find.setBrandImg(comp.getBrandImg());
		}catch(Exception e){
			log.error("CorpRepository update(CorpDomain) error --> {}", e);
		}
	}
	
	public Company findOne(String id) {
		Company company = em.find(Company.class, id);
        return company;
    }

    public List<Company> findAll() {
    	List<Company> list = new ArrayList<>();
    	try {
    		list = em.createQuery("select m from Member m", Company.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("CorpRepository findAll() error --> {}", e);
    	}
        return list;
    }
}
