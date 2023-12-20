package com.jobworld.project.repository;

import java.util.List;

import com.jobworld.project.domain.Member;
import jakarta.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobworld.project.domain.Recruit;

import lombok.RequiredArgsConstructor;

public interface HomeRepository extends JpaRepository<Recruit, Long>  {

	@Query("select r from Recruit r where r.company.id=:compId")
	public List<Recruit> findByCompId(String compId);
}
