
const {fetchAccounts}=require('../services/fetchAccountService');
const {createAccount} = require('../services/createAccountService.js');
const {depositAmount} = require('../services/depositAmountService.js');
const {withdrawAmount} = require('../services/withdrawAmountService.js');
const {transferAmount} = require('../services/transferAmountService.js');


 const createAccountMethod=async (req,res)=>{
   let obj=await createAccount(req,res);
 }

 const fetchAccountMethod=async (req,res)=>{
   let obj=await fetchAccounts(req,res);
 }
 const depositAmoutMethod=async(req,res)=>{
   await depositAmount(req,res);
 }
 const withdrawAmoutMethod=async(req,res)=>{
   await withdrawAmount(req,res);
 }
 const transferAmountMethod=async(req,res)=>{
   await transferAmount(req,res);
 }

    
    module.exports = {createAccountMethod,fetchAccountMethod,depositAmoutMethod,withdrawAmoutMethod,transferAmountMethod};