package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CompRepository {
	private final EntityManager em;
	
	public void save(Company corp) throws Exception {
		try {
			em.persist(corp);
		}catch(Exception e) {
			log.error("CorpRepository save(CorpDomain) error --> {}", e);
		}
	}
	
	public void update(Company corp) throws Exception {
		Company find;
		try {
			find = findOne(corp.getId());
			find.setPw(corp.getPw());
			find.setName(corp.getName());
			find.setBusinessType(corp.getBusinessType());
			find.setEmplNum(corp.getEmplNum());
			find.setSize(corp.getSize());
			find.setSite(corp.getSite());
		}catch(Exception e){
			log.error("CorpRepository update(CorpDomain) error --> {}", e);
		}
	}
	
	public Company findOne(String id) throws Exception {
		Company find = null;
		try {
			find = em.find(Company.class, id);
		}catch(Exception e) {
			log.error("CorpRepository findOne(String) error --> {}", e);
		}
        return find;
    }

    public List<Company> findAll() throws Exception {
    	List<Company> list = null;
    	try {
    		list = em.createQuery("select m from Member m", Company.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("CorpRepository findAll() error --> {}", e);
    	}
        return list;
    }
}
