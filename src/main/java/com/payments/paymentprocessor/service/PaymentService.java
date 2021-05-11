package com.payments.paymentprocessor.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.payments.paymentprocessor.dto.PaymentDTO;
import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.iban.CountyCodeChecker;
import com.payments.paymentprocessor.iban.RegexChecker;
import com.payments.paymentprocessor.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.List;
import java.util.Optional;

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

    public List<Payment> getAllPaymentsByIBAN(String iban) {
        return paymentRepository.getAllPaymentsByIBAN(iban);
    }

    public void addNewPayment(PaymentDTO paymentDTO) {

        Payment payment = new Payment();
        modelMapper.map(paymentDTO, payment);

        paymentRepository.save(payment);
    }

    public void addNewPaymentsFromFile(Reader reader) {

        // create csv bean reader
        CsvToBean<PaymentDTO> csvToBean = new CsvToBeanBuilder(reader)
                .withType(PaymentDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        // convert `CsvToBean` object to list of users
        List<PaymentDTO> paymentsDTO = csvToBean.parse();

        int counter = 0;
        for (PaymentDTO paymentDTO: paymentsDTO) {

            // Check for amount greater than 0.0
            if( paymentDTO.getAmount() > 0 &&
                CountyCodeChecker.validCode(paymentDTO.getDebtorIban()) &&
                RegexChecker.regexValid(paymentDTO.getDebtorIban())) {

                addNewPayment(paymentDTO);
                counter++;
            }
        }
        System.out.println(
                "Successfully added " + counter + "/" + paymentsDTO.size() +  " payment entries from file"
        );
    }
}
