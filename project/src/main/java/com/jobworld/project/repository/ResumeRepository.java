package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Resume;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ResumeRepository {
	
	private final EntityManager em;
	
	public void save(Resume resume) {
		try {
			em.persist(resume);			
		}catch(Exception e) {
			log.error("ResumeRepository save(ResumeDomain) error --> {}", e);
		}
	}
	
	public void update(Resume resume) {
		Resume find = findOne(resume.getId());
			
			find.setImg(resume.getImg());
			find.setTitle(resume.getTitle());
	}
	
	public Resume findOne(int id) {
		Resume find = em.find(Resume.class, id);
		
        return find;
    }
	
//	public List<Resume> findByName(String user_id) {
//		return em.createQuery("select r from Resume r where r.member.id = :user_id", Resume.class)
//				.setParameter("user_id", user_id)
//				.getResultList();
//	}
	
	public List<Resume> findByName(String user_id) {
		return em.createQuery("select r from Resume r where r.member.id = :user_id", Resume.class)
				.setParameter("user_id", user_id)
				.getResultList();
	}
	
	public List<Resume> findById(Long id){
		return em.createQuery("select r from Resume r where r.recruit.id = :id", Resume.class)
				.setParameter("id", id)
				.getResultList();
	}
	 
}
