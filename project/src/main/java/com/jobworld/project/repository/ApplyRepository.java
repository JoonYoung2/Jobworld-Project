package com.jobworld.project.repository;

import com.jobworld.project.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    @Query("select a from Apply a where " +
            "a.resume.id = :resumeId")
    List<Apply> findByResumeId(Long resumeId);

    @Query("select a from Apply a where " +
            "a.recruit.id = :recruitId")
    List<Apply> findByRecruitId(Long recruitId);

    @Query("select a from Apply a where " +
            "a.recruit.id = :recruitId and " +
            "a.resume.id = :resumeId")
    Optional<Apply> checkApplyUser(@Param("recruitId") Long recruitId, @Param("resumeId") Long resumeId);
}