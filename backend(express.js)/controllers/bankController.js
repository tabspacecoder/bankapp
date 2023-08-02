
const {fetchBanks}=require('../services/fetchBanksService.js');
const {createBank}=require('../services/createBankService.js')


 const fetchBanksMethod=async (req,res)=>{
   let obj=await fetchBanks(req,res);
 }
 const createBanksMethod=async (req,res)=>{
   let obj=await createBank(req,res);
}

    
    module.exports = {fetchBanksMethod,createBanksMethod};