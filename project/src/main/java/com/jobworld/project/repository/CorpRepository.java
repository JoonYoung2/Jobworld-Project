package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.CorpDomain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CorpRepository {
	private final EntityManager em;
	
	public void save(CorpDomain corp) throws Exception {
		try {
			em.persist(corp);
		}catch(Exception e) {
			log.error("CorpRepository save(CorpDomain) error --> {}", e);
		}
	}
	
	public void update(CorpDomain corp) throws Exception {
		CorpDomain find;
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
	
	public CorpDomain findOne(String id) throws Exception {
		CorpDomain find = null;
		try {
			find = em.find(CorpDomain.class, id);
		}catch(Exception e) {
			log.error("CorpRepository findOne(String) error --> {}", e);
		}
        return find;
    }

    public List<CorpDomain> findAll() throws Exception {
    	List<CorpDomain> list = null;
    	try {
    		list = em.createQuery("select m from Member m", CorpDomain.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("CorpRepository findAll() error --> {}", e);
    	}
        return list;
    }
}
