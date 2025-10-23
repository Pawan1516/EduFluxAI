package com.EduFluxAI.backend.repository;

import com.EduFluxAI.backend.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Submission> findByChallengeId(Long challengeId);
}
