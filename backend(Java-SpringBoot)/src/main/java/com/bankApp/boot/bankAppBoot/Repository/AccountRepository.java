package com.bankApp.boot.bankAppBoot.Repository;

import com.bankApp.boot.bankAppBoot.Model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountModel, Integer> {
    @Query("from AccountModel where customerId=?1")
    List<AccountModel> fetchAllAccountsById(Integer customerId);
}
