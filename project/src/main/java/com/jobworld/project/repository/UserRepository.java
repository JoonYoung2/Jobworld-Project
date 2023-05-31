package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.ApplyDomain;
import com.jobworld.project.domain.UserDomain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository {
	
	private final EntityManager em;
	
	public void save(UserDomain user) throws Exception {
		try {
			em.persist(user);			
		}catch(Exception e) {
			log.error("UserRepository save(UserDomain) error --> {}", e);
		}
	}
	
	public void update(UserDomain user) throws Exception {
		UserDomain findId = null;
		try {
			findId = findOne(user.getId());
			
			findId.setAddress_detail(user.getAddress_detail());
			findId.setAddress_info(user.getAddress_info());
			findId.setZip_cd(user.getZip_cd());
			findId.setBirthday(user.getBirthday());
			findId.setEmail(user.getEmail());
			findId.setName(user.getName());
			findId.setPhoneNum(user.getPhoneNum());
			findId.setPw(user.getPw());			
		}catch(Exception e) {
			log.error("UserRepository update(UserDomain) error --> {}", e);
		}
	}
	
	public void delete(String id) throws Exception{
		try {
			UserDomain userFind = findOne(id);
			em.remove(userFind.getId());
		}catch(Exception e){
			log.error("UserRepository delete(String) error --> {}", e);
		}
	}
	
	public UserDomain findOne(String id) throws Exception {
		UserDomain find = null;
		try {
			find = em.find(UserDomain.class, id);
		}catch(Exception e) {
			log.error("UserRepository findOne(String) error --> {}", e);
		}
        return find;
    }

    public List<UserDomain> findAll() throws Exception {
    	List<UserDomain> list = null;
    	try {
    		list = em.createQuery("select m from Member m", UserDomain.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("UserRepository findAll() error --> {}", e);
    	}
        return list;
    }
	
}