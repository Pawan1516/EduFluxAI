package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.Challenge;
import com.EduFluxAI.backend.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public List<Challenge> findAll() {
        return challengeRepository.findAll();
    }

    public Challenge findById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));
    }

    public Challenge save(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge update(Long id, Challenge details) {
        Challenge existing = findById(id);
        existing.setTitle(details.getTitle());
        existing.setDifficulty(details.getDifficulty());
        existing.setDescription(details.getDescription());
        existing.setSampleInput(details.getSampleInput());
        existing.setSampleOutput(details.getSampleOutput());
        existing.setStarterCode(details.getStarterCode());
        return challengeRepository.save(existing);
    }

    public void delete(Long id) { challengeRepository.deleteById(id); }
}
