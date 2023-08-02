package com.bankApp.boot.bankAppBoot.Repository;

import com.bankApp.boot.bankAppBoot.Model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.ArrayList;

public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
    @Query("from TransactionModel where customerId=?1")
    ArrayList<TransactionModel> fetchAllAccountsById(Integer customerId);
}
