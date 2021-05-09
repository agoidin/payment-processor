package com.payments.paymentprocessor.controller;

import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/payments")
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @PostMapping(path = "/payments")
    public void addPayment(@RequestBody Payment payment) {
        paymentService.addNewPayment(payment);
    }
}
