package com.bankApp.boot.bankAppBoot.Controller;

import com.bankApp.boot.bankAppBoot.Model.*;
import com.bankApp.boot.bankAppBoot.Repository.AccountRepository;
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
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    public Map<String,String> MessageModel(String s){
        Map<String,String > ret= new HashMap<>();
        ret.put("message",s);
        return ret;
    }
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @PostMapping("/fetch-accounts")
    public ResponseEntity<Object> getAccounts(@RequestBody FetchRequestModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                List<AccountModel> ret=accountRepository.fetchAllAccountsById(req.getCustomerId());
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

    @PostMapping("/create-account")
    public ResponseEntity<Object> createAccount(@RequestBody CreateAccountModel req){

        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                AccountModel accountModel=new AccountModel();
                accountModel.setBalance(req.getBalance());
                accountModel.setCustomerId(req.getCustomerId());
                accountModel.setBankId(req.getBankId());
                AccountModel ret=accountRepository.save(accountModel);
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
    @PostMapping("/transfer-amount")
    public ResponseEntity<Object> transferAmount(@RequestBody TransferModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                Optional<AccountModel> depositAcc=accountRepository.findById(req.getDepositAccountId());
                Optional<AccountModel> withdrawAcc=accountRepository.findById(req.getWithdrawAccountId());
                if(withdrawAcc.isPresent()){
                    AccountModel wacc=withdrawAcc.get();
                    if(wacc.getBalance()<req.getAmount()){
                        return new ResponseEntity<>(MessageModel("Insufficient Funds"), HttpStatus.valueOf(400));
                    }
                    else{
                        if(depositAcc.isPresent()){
                            wacc.setBalance(wacc.getBalance()- req.getAmount());
                            accountRepository.save(wacc);
                            AccountModel dacc=depositAcc.get();
                            dacc.setBalance(dacc.getBalance()+ req.getAmount());
                            accountRepository.save(dacc);
                            transactionRepository.save(new TransactionModel(req.getAmount(),"CREDIT",req.getDepositAccountId(),dacc.getCustomerId(),dacc.getBankId()));
                            transactionRepository.save(new TransactionModel(req.getAmount(),"DEBIT",req.getWithdrawAccountId(),wacc.getCustomerId(),wacc.getBankId()));
                            return new ResponseEntity<>(MessageModel("Transaction Successful"), HttpStatus.valueOf(200));
                        }
                        else{
                            return new ResponseEntity<>(MessageModel("Deposit account not found"), HttpStatus.valueOf(400));
                        }



                    }
                }
                else{
                    return new ResponseEntity<>(MessageModel("Withdraw account not found"), HttpStatus.valueOf(400));
                }
            }
            else{
                return new ResponseEntity<>(MessageModel("Passwords not matching!"), HttpStatus.valueOf(400));
            }

        }
        else{
            return new ResponseEntity<>(MessageModel("Customer ID not found!"), HttpStatus.valueOf(400));
        }


    }

    @PostMapping("/deposit-amount")
    public  ResponseEntity<Object> depositAmount(@RequestBody MoneyModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                Optional<AccountModel> ret=accountRepository.findById(req.getCustomerId());
                if(ret.isPresent()){
                    AccountModel acc=ret.get();
                    acc.setBalance(acc.getBalance()+req.getAmount());
                    accountRepository.save(acc);
                    TransactionModel tra=new TransactionModel();
                    tra.setAmount(req.getAmount());
                    tra.setType("CREDIT");
                    tra.setAccountId(req.getAccountId());
                    tra.setCustomerId(req.getCustomerId());
                    tra.setBankId(acc.getBankId());
                    TransactionModel res=transactionRepository.save(tra);
                    return new ResponseEntity<>(MessageModel("Deposited amount successfully"), HttpStatus.valueOf(200));
                }
                else{
                    return new ResponseEntity<>(MessageModel("Account not found!"), HttpStatus.valueOf(400));
                }

            }
            else{
                return new ResponseEntity<>(MessageModel("Passwords not matching!"), HttpStatus.valueOf(400));
            }

        }
        else{
            return new ResponseEntity<>(MessageModel("Customer ID not found!"), HttpStatus.valueOf(400));
        }

    }
    @PostMapping("/withdraw-amount")
    public  ResponseEntity<Object> withdrawAmount(@RequestBody MoneyModel req){
        Optional<CustomerModel> customerModel=customerRepository.findById(req.getCustomerId());
        if(customerModel.isPresent()){
            if(customerModel.get().getPassword().equals(req.getPassword()))
            {
                Optional<AccountModel> ret=accountRepository.findById(req.getCustomerId());
                if(ret.isPresent()){
                    AccountModel acc=ret.get();
                    if(req.getAmount()>acc.getBalance()){
                        return new ResponseEntity<>(MessageModel("Insufficient Funds!"), HttpStatus.valueOf(400));
                    }
                    acc.setBalance(acc.getBalance()-req.getAmount());
                    accountRepository.save(acc);
                    transactionRepository.save(new TransactionModel(req.getAmount(),"DEBIT",req.getAccountId(),acc.getCustomerId(),acc.getBankId()));
                    return new ResponseEntity<>(MessageModel("Withdrawed amount successfully"), HttpStatus.valueOf(200));
                }
                else{
                    return new ResponseEntity<>(MessageModel("Account not found!"), HttpStatus.valueOf(400));
                }

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
