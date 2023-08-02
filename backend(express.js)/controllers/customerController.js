
const loginService=require('../services/loginService.js');
const {createCustomer} = require('../services/createCustomerService.js')
const {fetchAllCustomerDetails}=require('../services/fetchAllCustomerDetailsService.js')

 const loginCustomer=async (req,res)=>{
  let obj=await loginService(req,res);
 }

 const createCustomerMethod=async (req,res)=>{
   let obj=await createCustomer(req,res);
 }

 const fetchAllCustomerDetailsMethod=async (req,res)=>{
  let obj=await fetchAllCustomerDetails(req,res);
 }
    
    module.exports = {loginCustomer,createCustomerMethod,fetchAllCustomerDetailsMethod};