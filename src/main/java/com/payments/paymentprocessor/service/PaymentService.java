package com.payments.paymentprocessor.service;

import com.payments.paymentprocessor.dto.PaymentDTO;
import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.iban.RegexChecker;
import com.payments.paymentprocessor.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Payment> getPayments() { return paymentRepository.findAll(); }

    public void addNewPayment(PaymentDTO paymentDTO) {

        /*if(!RegexChecker.regexValid(payment.getDebtorIban())) {
            throw new IllegalStateException(
                    "invalid account number format"
            );
        }*/

        Payment payment = new Payment();
        modelMapper.map(paymentDTO, payment);

        paymentRepository.save(payment);
    }
}
