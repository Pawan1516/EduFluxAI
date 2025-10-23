package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.Streak;
import com.EduFluxAI.backend.repository.StreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StreakService {

    @Autowired
    private StreakRepository streakRepository;

    public Streak incrementStreak(Long userId) {
        Streak streak = streakRepository.findByUserId(userId).orElseGet(() -> {
            Streak s = new Streak();
            s.setUserId(userId);
            s.setCount(0);
            return s;
        });

        LocalDate today = LocalDate.now();
        if (streak.getLastActive() == null || streak.getLastActive().isEqual(today.minusDays(1))) {
            streak.setCount(streak.getCount() + 1);
        } else if (!today.isEqual(streak.getLastActive())) {
            streak.setCount(1);
        }
        streak.setLastActive(today);
        return streakRepository.save(streak);
    }
}
