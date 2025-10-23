package com.EduFluxAI.backend.controller;

import com.EduFluxAI.backend.dto.ApiResponse;
import com.EduFluxAI.backend.entity.Payment;
import com.EduFluxAI.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Payment>> record(@RequestBody Payment payment) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment recorded", paymentService.recordPayment(payment)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Payment>>> byUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments retrieved", paymentService.getPaymentsForUser(userId)));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<Payment>>> byCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments retrieved", paymentService.getPaymentsForCourse(courseId)));
    }
}
