const { where } = require('sequelize');
const db= require('../utils/connectionUtil')
const Accounts = db.account;
const Customer = db.customer;
const fetchAccounts = async (req,res)=>{
    
    let customer=await Customer.findOne({where:{id:req.query.customerId}});
    if(customer==null){
        res.status(400).send({'message':'Customer not found !'})
    }
    else{
        if(customer.password==req.query.password){
            let accounts =await Accounts.findAll({where:{customerId:req.query.customerId}});
            res.status(200).send(accounts);
        }
        else{
            res.status(400).send({'message':'Password Incorrect'})
        }
    }

    

}

module.exports={fetchAccounts}