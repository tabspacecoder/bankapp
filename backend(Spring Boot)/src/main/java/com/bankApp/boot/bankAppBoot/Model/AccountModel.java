package com.bankApp.boot.bankAppBoot.Model;

import jakarta.persistence.*;

@Entity
public class AccountModel {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    Integer id;
    Double balance;

    Integer customerId;

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", balance=" + balance +
                ", customerId=" + customerId +
                ", bankId=" + bankId +
                '}';
    }

    public AccountModel(Integer id, Double balance, Integer customerId, Integer bankId) {
        this.id = id;
        this.balance = balance;
        this.customerId = customerId;
        this.bankId = bankId;
    }
    public AccountModel(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    Integer bankId;
}
