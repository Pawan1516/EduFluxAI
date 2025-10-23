package com.EduFluxAI.backend.service;

import com.EduFluxAI.backend.entity.Payment;
import com.EduFluxAI.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment recordPayment(Payment payment) { return paymentRepository.save(payment); }

    public List<Payment> getPaymentsForUser(Long userId) { return paymentRepository.findByUserIdOrderByTimestampDesc(userId); }

    public List<Payment> getPaymentsForCourse(Long courseId) { return paymentRepository.findByCourseId(courseId); }
}
