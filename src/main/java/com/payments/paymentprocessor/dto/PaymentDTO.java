package com.payments.paymentprocessor.dto;

import com.opencsv.bean.CsvBindByName;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentDTO {

    private UUID uuid;

    @CsvBindByName(column = "amount")
    @NotNull(message = "Enter amount")
    private Double amount;

    @CsvBindByName(column = "debtorIban")
    @NotBlank(message = "Enter debtor IBAN")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", message = "IBAN format is invalid")
    private String debtorIban;

    private LocalDateTime createdAt;

    public PaymentDTO() { }

    public PaymentDTO(Double amount, String debtorIban) {
        this.amount = amount;
        this.debtorIban = debtorIban;
    }

    public PaymentDTO(UUID uuid, Double amount, String debtorIban, LocalDateTime createdAt) {
        this.uuid = uuid;
        this.amount = amount;
        this.debtorIban = debtorIban;
        this.createdAt = createdAt;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "uuid=" + uuid +
                ", amount=" + amount +
                ", debtorIban='" + debtorIban + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
