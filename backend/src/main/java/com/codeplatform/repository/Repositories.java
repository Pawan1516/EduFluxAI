package com.codeplatform.repository;

import com.codeplatform.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

@Repository interface CourseRepository extends JpaRepository<Course, Long> {}

@Repository interface LessonRepository extends JpaRepository<Lesson, Long> {}

@Repository interface ChallengeRepository extends JpaRepository<Challenge, Long> {}

@Repository interface SubmissionRepository extends JpaRepository<Submission, Long> {}

@Repository interface PaymentRepository extends JpaRepository<Payment, Long> {}

@Repository interface StreakRepository extends JpaRepository<Streak, Long> {}

@Repository interface AILessonRepository extends JpaRepository<AILesson, Long> {}


