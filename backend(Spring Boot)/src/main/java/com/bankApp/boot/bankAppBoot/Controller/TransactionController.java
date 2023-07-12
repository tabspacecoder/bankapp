package com.bankApp.boot.bankAppBoot.Controller;

import com.bankApp.boot.bankAppBoot.Model.CustomerModel;
import com.bankApp.boot.bankAppBoot.Model.FetchRequestModel;
import com.bankApp.boot.bankAppBoot.Model.TransactionModel;
import com.bankApp.boot.bankAppBoot.Repository.CustomerRepository;
import com.bankApp.boot.bankAppBoot.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {
    public Map<String,String> MessageModel(String s){
        Map<String,String > ret= new HashMap<>();
        ret.put("message",s);
        return ret;
    }
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/fetch-transactions")
    public ResponseEntity<Object> fetchTransactions(@RequestBody FetchRequestModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                List<TransactionModel> ret=transactionRepository.fetchAllAccountsById(req.getCustomerId());
                return new ResponseEntity<>(ret, HttpStatus.valueOf(200));
            }
            else{
                return new ResponseEntity<>(MessageModel("Passwords not matching!"), HttpStatus.valueOf(400));
            }

        }
        else{
            return new ResponseEntity<>(MessageModel("Customer ID not found!"), HttpStatus.valueOf(400));
        }



    }
    }

