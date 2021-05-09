package com.payments.paymentprocessor.repository;

import com.payments.paymentprocessor.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
