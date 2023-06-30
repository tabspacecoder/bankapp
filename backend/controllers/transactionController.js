
const {fetchTransaction}=require('../services/fetchTransactionService.js');
const {createTransaction} = require('../services/createTransactionService.js')


 const createTransactionMethod=async (req,res)=>{
   let obj=await createTransaction(req,res);
 }

 const fetchTransactionMethod=async (req,res)=>{
   let obj=await fetchTransaction(req,res);
 }

    
    module.exports = {createTransactionMethod,fetchTransactionMethod};