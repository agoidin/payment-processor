package com.payments.paymentprocessor.service;

import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.iban.CountyCodeChecker;
import com.payments.paymentprocessor.iban.RegexChecker;
import com.payments.paymentprocessor.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayments() { return paymentRepository.findAll(); }

    public void addNewPayment(Payment payment) {
//         Check for amount greater than 0.0
        if(payment.getAmount() <= 0) {
            throw new IllegalStateException(
                    "amount cannot be less or equal to 0.0"
            );
        }

        if(!RegexChecker.regexValid(payment.getDebtorIban())) {
            throw new IllegalStateException(
                    "invalid account number format"
            );
        }

//        Check for IBAN country code
        if(!CountyCodeChecker.validCode(payment.getDebtorIban())) {

            throw new IllegalStateException(
                    "only Baltic account numbers are supported"
            );
        }

        paymentRepository.save(payment);
    }
}
