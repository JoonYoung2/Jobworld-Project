package com.jobworld.project.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobworld.project.domain.Member;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ResumeDTO;

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
	 
}
