package com.payments.paymentprocessor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private String debtorIban;
    private Double amount;

    public Payment() { }


    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
//    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationTime;

    public Payment(UUID uuid, String debtorIban, Double amount) {
        this.uuid = uuid;
        this.debtorIban = debtorIban;
        this.amount = amount;
//        this.creationTime = LocalDateTime.now();
    }

    public Payment(String debtorIban, Double amount) {
        this.debtorIban = debtorIban;
        this.amount = amount;
//        this.creationTime = LocalDateTime.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

//    public LocalDateTime getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime() {
//        this.creationTime = LocalDateTime.now();
//    }
}
