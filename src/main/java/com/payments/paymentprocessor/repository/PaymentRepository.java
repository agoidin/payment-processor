package com.payments.paymentprocessor.repository;

import com.payments.paymentprocessor.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query(
            value = "SELECT * FROM Payments p WHERE p.debtor_iban = ?1",
            nativeQuery = true
    )
    List<Payment> getAllPaymentsByIBAN(String iban);
}
