package com.bankApp.boot.bankAppBoot.Controller;

import com.bankApp.boot.bankAppBoot.Model.BankModel;
import com.bankApp.boot.bankAppBoot.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/bank")
public class BankController{
    @Autowired
    BankRepository bankRepository;
    @PostMapping("/fetch-banks")
    public ResponseEntity<List<BankModel>> getAllBanks(){
        List<BankModel> banks = new ArrayList<BankModel>();
        bankRepository.findAll().forEach(banks::add);
        return new ResponseEntity<>(banks, HttpStatus.valueOf(200));
    }
    @PostMapping("/create-bank")
    public ResponseEntity<BankModel> createBank(@RequestBody BankModel bank){
        BankModel _bank = bankRepository.save(bank);
        return new ResponseEntity<>(_bank, HttpStatus.valueOf(200));
    }
}
