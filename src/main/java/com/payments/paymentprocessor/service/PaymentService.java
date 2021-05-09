package com.payments.paymentprocessor.service;

import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayments() { return paymentRepository.findAll(); }
}
