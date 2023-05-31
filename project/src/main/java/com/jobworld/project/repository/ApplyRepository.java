package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.ApplyDomain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ApplyRepository {
	
	private final EntityManager em;
	
	public void save(ApplyDomain apply) throws Exception {
		try {
			em.persist(apply);
		}catch(Exception e) {
			log.error("ApplyRepository save(ApplyDomain) error --> {}", e);
		}
	}
	
	public void update(ApplyDomain apply) throws Exception {
		ApplyDomain find;
		
		try {
			find = findOne(apply.getId());
			find.setState(apply.getState());
		}catch(Exception e) {
			log.error("ApplyRepository update(ApplyDomain) error --> {}", e);
		}
	}
	
	public ApplyDomain findOne(Long id) throws Exception {
		ApplyDomain find = null;
		try {
			find = em.find(ApplyDomain.class, id);
		}catch(Exception e) {
			log.error("ApplyRepository findOne(Long) error --> {}", e);
		}
        return find;
    }

    public List<ApplyDomain> applyUserFindAll(String user_id) throws Exception {
    	List<ApplyDomain> list = null;
    	try {
    		list = em.createQuery("select m from Member m where m.user_id = :user_id", ApplyDomain.class)
            		.setParameter("user_id", user_id)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("ApplyRepository applyUserFindAll(String) error --> {}", e);
    	}
        return list;
    }
    
    public List<ApplyDomain> corpApplyInfofindAll(String corp_id) throws Exception {
    	List<ApplyDomain> list = null;
    	try {
    		list = em.createQuery("select m from Member m where m.corp_id = :corp_id", ApplyDomain.class)
            		.setParameter("corp_id", corp_id)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("ApplyRepository corpApplyInfoFindAll(String) error --> {}", e);
    	}
        return list;
    }
    
    public List<ApplyDomain> findAll() throws Exception {
    	List<ApplyDomain> list = null;
    	try {
    		list = em.createQuery("select m from Member m", ApplyDomain.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("ApplyRepository findAll() error --> {}", e);
    	}
        return list;
    }
}
