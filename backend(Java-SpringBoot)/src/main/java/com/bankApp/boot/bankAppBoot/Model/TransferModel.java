package com.bankApp.boot.bankAppBoot.Model;

public class TransferModel {
    Integer customerId;
    String password;
    Double amount;
    Integer depositAccountId;
    Integer withdrawAccountId;
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDepositAccountId() {
        return depositAccountId;
    }

    public void setDepositAccountId(Integer depositAccountId) {
        this.depositAccountId = depositAccountId;
    }

    public Integer getWithdrawAccountId() {
        return withdrawAccountId;
    }

    public void setWithdrawAccountId(Integer withdrawAccountId) {
        this.withdrawAccountId = withdrawAccountId;
    }


}
