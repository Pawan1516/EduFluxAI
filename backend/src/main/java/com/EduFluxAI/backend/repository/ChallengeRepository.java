package com.EduFluxAI.backend.repository;

import com.EduFluxAI.backend.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByDifficulty(Challenge.Difficulty difficulty);
}
