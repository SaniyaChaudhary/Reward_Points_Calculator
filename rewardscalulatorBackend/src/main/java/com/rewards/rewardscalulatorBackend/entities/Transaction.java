package com.rewards.rewardscalulatorBackend.entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Table(name = "transaction_info")
@Entity
public class Transaction {

        @Id
        private Integer transactionId;
        private Integer customerId;

        private LocalDate transactionDate;

        private double amount;
        public Transaction(Integer transactionId, Integer customerId, LocalDate transactionDate, double amount) {
         this.transactionId = transactionId;
         this.customerId = customerId;
         this.transactionDate = transactionDate;
         this.amount = amount;
    }

    public Transaction() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
