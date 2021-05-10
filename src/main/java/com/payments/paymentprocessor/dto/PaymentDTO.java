package com.payments.paymentprocessor.dto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentDTO {

    private UUID uuid;

    @NotBlank(message = "Enter debtor IBAN")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?:[ ]?[0-9]{1,2})?$", message = "IBAN format is invalid")
    private String debtorIban;

    @NotNull(message = "Enter amount")
    private Double amount;
    private LocalDateTime createdAt;

    public PaymentDTO() { }

    public PaymentDTO(String debtorIban, Double amount) {
        this.debtorIban = debtorIban;
        this.amount = amount;
    }

    public PaymentDTO(UUID uuid, String debtorIban, Double amount, LocalDateTime createdAt) {
        this.uuid = uuid;
        this.debtorIban = debtorIban;
        this.amount = amount;
        this.createdAt = createdAt;
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
                ", debtorIban='" + debtorIban + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
