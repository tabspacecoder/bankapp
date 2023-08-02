package com.bankApp.boot.bankAppBoot.Repository;

import com.bankApp.boot.bankAppBoot.Model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<BankModel, Integer> {
    @Override
    List<BankModel> findAll();
}
