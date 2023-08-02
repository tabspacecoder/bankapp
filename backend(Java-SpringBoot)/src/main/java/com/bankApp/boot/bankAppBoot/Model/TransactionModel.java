package com.bankApp.boot.bankAppBoot.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionModel {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    Integer id;
    Double amount;
    String type;
    Integer accountId;
    Integer customerId;
    Integer bankId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", accountId=" + accountId +
                ", customerId=" + customerId +
                ", bankId=" + bankId +
                '}';
    }

    public TransactionModel( Double amount, String type, Integer accountId, Integer customerId, Integer bankId) {
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
        this.customerId = customerId;
        this.bankId = bankId;
    }
    public TransactionModel( Integer id,Double amount, String type, Integer accountId, Integer customerId, Integer bankId) {
        this.id=id;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
        this.customerId = customerId;
        this.bankId = bankId;
    }
    public TransactionModel(){

    }
}
