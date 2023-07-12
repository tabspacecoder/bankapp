package com.bankApp.boot.bankAppBoot.Repository;


import com.bankApp.boot.bankAppBoot.Model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
}
