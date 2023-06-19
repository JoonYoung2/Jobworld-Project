package com.jobworld.project.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {
	
	private final EntityManager em;
	
	public void save(Member member) throws Exception {
		try {
			em.persist(member);			
		}catch(Exception e) {
			log.error("MemberRepository save(UserDomain) error --> {}", e);
		}
	}
	
	public void delete(String id) throws Exception{
		try {
			Member member = findOne(id);
			em.remove(member.getId());
		}catch(Exception e){
			log.error("MemberRepository delete(String) error --> {}", e);
		}
	}
	
	public Member findOne(String id) throws Exception {
		Member member = new Member();
		try {
			member = em.find(Member.class, id);
		}catch(Exception e) {
			log.error("MemberRepository findOne(String) error --> {}", e);
		}
        return member;
    }

    public List<Member> findAll() throws Exception {
    	List<Member> list = new ArrayList<>();
    	try {
    		list = em.createQuery("select m from Member m", Member.class)
            		.getResultList();
    	}catch(Exception e) {
    		log.error("MemberRepository findAll() error --> {}", e);
    	}
        return list;
    }
	
}