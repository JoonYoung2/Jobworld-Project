package com.jobworld.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobworld.project.domain.Recruit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long>  {
    @Query("SELECT r FROM Recruit r WHERE r.company.id = :companyId")
    List<Recruit> findByCompId(String companyId);
}
