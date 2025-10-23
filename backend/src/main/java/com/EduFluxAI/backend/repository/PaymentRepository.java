package com.EduFluxAI.backend.repository;

import com.EduFluxAI.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserIdOrderByTimestampDesc(Long userId);
    List<Payment> findByCourseId(Long courseId);
}
