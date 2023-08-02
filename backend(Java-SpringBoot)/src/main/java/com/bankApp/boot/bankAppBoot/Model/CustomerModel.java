package com.bankApp.boot.bankAppBoot.Model;

import jakarta.persistence.*;

import java.lang.ref.Reference;

@Entity
public class CustomerModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String name;
    String password;
    String email;
    Integer bankId;

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", bankId=" + bankId +
                '}';
    }

    public CustomerModel(Integer id, String name, String password, String email, Integer bankId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.bankId = bankId;
    }

    public CustomerModel(){

    };
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
}
