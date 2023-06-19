package com.jobworld.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ResumeRepository {
	
	private final EntityManager em;
	
	public void save(Resume resume) throws Exception {
		try {
			em.persist(resume);			
		}catch(Exception e) {
			log.error("ResumeRepository save(ResumeDomain) error --> {}", e);
		}
	}
	
	public void update(Resume resume) throws Exception {
		Resume find = null;
		try {
			find = findOne(resume.getId());
			
			find.setImg(resume.getImg());
			find.setTitle(resume.getTitle());
		}catch(Exception e) {
			log.error("ResumeRepository update(ResumeDomain) error --> {}", e);
		}
	}
	
	public Resume findOne(int id) throws Exception {
		Resume find = null;
		
		try {
			find = em.find(Resume.class, id);
		}catch(Exception e) {
			log.error("ResumeRepository findOne(int) error --> {}", e);
		}
        return find;
    }
	
	public Resume findUserId(String user_id) throws Exception {
		Resume find = new Resume();
		
		try {
			System.out.println("sadflsdflsdjaklfjasklfjaskljfdsla");
			List<Resume> list = em.createQuery("select r from Resume r where r.user_id = :user_id", Resume.class)
					.setParameter("user_id", user_id)
					.getResultList();
			System.out.println("listsize" + list.size());
			System.out.println("sadflsdflsdjaklfjasklfjaskljfdsla222");
//			List<Resume> list = typedQuery.getResultList();
			System.out.println("sadflsdflsdjaklfjasklfjaskljfdsla333");
//			find = list.get(0);
			System.out.println("sadflsdflsdjaklfjasklfjaskljfdsla444");
		}catch(IllegalArgumentException e) {
			log.error("ResumeRepository findOne(String) error --> {}", e);
		}
        return find;
    }
	
}
