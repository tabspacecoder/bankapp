const { where } = require('sequelize');
const db= require('../utils/connectionUtil')
const Transactions = db.transaction;
const Customer = db.customer;
const fetchTransaction = async (req,res)=>{
    
    let customer=await Customer.findOne({where:{id:req.query.customerId}});
    if(customer==null){
        res.status(400).send({'message':'Customer not found'})
    }
    else{
        if(customer.password==req.query.password){
            let transactions =await Transactions.findAll({where:{customerId:req.query.customerId}});
            res.status(200).send(transactions);
        }
        else{
            res.status(400).send({'message':'Password Incorrect'})
        }

    }

    

}

module.exports={fetchTransaction}