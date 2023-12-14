package com.jobworld.project.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryOld {
	
	private final EntityManager em;
	
	public void save(Member member) {
		try {
			em.persist(member);			
		}catch(Exception e) {
			
		}
	}
	
	public void delete(String id) {
		Member member = findOne(id);
		em.remove(member.getId());
	}
	
	public Member findOne(String id) {
		Member member = em.find(Member.class, id);
        return member;
    }

    public List<Member> findAll() {
    	List<Member> list = em.createQuery("select m from Member m", Member.class)
            		.getResultList();
        return list;
    }
	
}