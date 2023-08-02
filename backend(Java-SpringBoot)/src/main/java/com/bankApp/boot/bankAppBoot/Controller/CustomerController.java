package com.bankApp.boot.bankAppBoot.Controller;

import com.bankApp.boot.bankAppBoot.Model.*;
import com.bankApp.boot.bankAppBoot.Repository.AccountRepository;
import com.bankApp.boot.bankAppBoot.Repository.BankRepository;
import com.bankApp.boot.bankAppBoot.Repository.CustomerRepository;
import com.bankApp.boot.bankAppBoot.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    public Map<String,String> MessageModel(String s){
        Map<String,String > ret= new HashMap<>();
        ret.put("message",s);
        return ret;
    }
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BankRepository bankRepository;
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody FetchRequestModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
            if(customerModel.isPresent()){
                if(customerModel.get().getPassword().equals(req.getPassword()))
                {

                    return new ResponseEntity<>(MessageModel("Login Successful!"),HttpStatus.valueOf(200));
                }
                else{
                    return new ResponseEntity<>(MessageModel("Login Failed!"), HttpStatus.valueOf(400));
                }

            }
            else{
                return new ResponseEntity<>(MessageModel("Customer ID not found!"), HttpStatus.valueOf(400));
            }



    }
    @PostMapping("/fetch-all-details")
    public ResponseEntity<Object> fetchAllDetails(@RequestBody FetchRequestModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                CustomerModel retCustomer=customerModel.get();
                Optional<BankModel> bankModel=bankRepository.findById(retCustomer.getBankId());
                List<AccountModel> retAcc=accountRepository.fetchAllAccountsById(retCustomer.getId());
                ArrayList<TransactionModel> retTr=transactionRepository.fetchAllAccountsById(retCustomer.getId());
                Map<String, Object> map = new HashMap<String, Object>();
                if(!bankModel.isPresent()){
                    return new ResponseEntity<>(MessageModel("Bank not found!"),HttpStatus.valueOf(400));
                }
                map.put("bank",bankModel.get());
                map.put("customer",retCustomer);
                map.put("accounts",retAcc);
                map.put("transactions",retTr);
                return new ResponseEntity<>(map,HttpStatus.valueOf(200));
            }
            else{
                return new ResponseEntity<>(MessageModel("Login Failed!"), HttpStatus.valueOf(400));
            }

        }
        else{
            return new ResponseEntity<>(MessageModel("Customer ID not found!"), HttpStatus.valueOf(400));
        }



    }

    @PostMapping("/create-user")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerModel req){
        CustomerModel cus=customerRepository.save(req);
        return new ResponseEntity<>(cus,HttpStatus.valueOf(200));


    }


}
