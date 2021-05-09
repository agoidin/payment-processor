package com.payments.paymentprocessor.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Long uuid;
    private String debtorIban;
    private Double amount;
    private LocalDateTime creationTime;

    public Payment() { }

    public Payment(Long uuid, String debtorIban, Double amount, LocalDateTime creationTime) {
        this.uuid = uuid;
        this.debtorIban = debtorIban;
        this.amount = amount;
        this.creationTime = creationTime;
    }

    public Payment(String debtorIban, Double amount, LocalDateTime creationTime) {
        this.debtorIban = debtorIban;
        this.amount = amount;
        this.creationTime = creationTime;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
