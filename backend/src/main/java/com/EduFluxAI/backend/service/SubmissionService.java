package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.Submission;
import com.EduFluxAI.backend.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission create(Submission submission) { return submissionRepository.save(submission); }

    public List<Submission> getByUser(Long userId) { return submissionRepository.findByUserIdOrderByCreatedAtDesc(userId); }

    public List<Submission> getByChallenge(Long challengeId) { return submissionRepository.findByChallengeId(challengeId); }
}
