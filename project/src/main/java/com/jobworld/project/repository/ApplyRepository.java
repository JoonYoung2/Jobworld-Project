package com.jobworld.project.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ApplyRepository {
	
	private final EntityManager em;
	
	public void save(Apply apply) throws Exception {
		try {
			em.persist(apply);
		}catch(Exception e) {
			log.error("ApplyRepository save(ApplyDomain) error --> {}", e);
		}
	}
	
	public void update(Apply apply) throws Exception {
		Apply find = null;
		
		try {
			find = findOne(apply.getId());
			find.setState(apply.getState());
		}catch(Exception e) {
			log.error("ApplyRepository update(ApplyDomain) error --> {}", e);
		}
	}
	
	public Apply findOne(Long id) throws Exception {
		Apply find = null;
		try {
			find = em.find(Apply.class, id);
		}catch(Exception e) {
			log.error("ApplyRepository findOne(Long) error --> {}", e);
		}
        return find;
    }

    public List<Apply> applyUserFindAll(String user_id) throws Exception {
    	List<Apply> list = new ArrayList<>();
    	try {
    		list = em.createQuery("select m from Member m where m.user_id = :user_id", Apply.class)
            		.setParameter("user_id", user_id)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("ApplyRepository applyUserFindAll(String) error --> {}", e);
    	}
        return list;
    }
    
    public List<Apply> corpApplyInfofindAll(String corp_id) throws Exception {
    	List<Apply> list = new ArrayList<>();
    	try {
    		list = em.createQuery("select m from Member m where m.corp_id = :corp_id", Apply.class)
            		.setParameter("corp_id", corp_id)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("ApplyRepository corpApplyInfoFindAll(String) error --> {}", e);
    	}
        return list;
    }
    
    public List<Apply> findAll() throws Exception {
    	List<Apply> list = new ArrayList<>();
    	try {
    		list = em.createQuery("select m from Member m", Apply.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("ApplyRepository findAll() error --> {}", e);
    	}
        return list;
    }
}
