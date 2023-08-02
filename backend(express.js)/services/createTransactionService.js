const { where } = require('sequelize');
const db= require('../utils/connectionUtil')
const Transactions = db.transaction;
const createTransaction = async (req,res)=>{
    let info = {
        amount:req.query.amount,
        type:req.query.type,
        accountId:req.query.accountId,
        bankId:req.query.bankId,
        customerId:req.query.customerId
    }

    const tranaction=await Transactions.create(info);
    res.status(200).send(tranaction);

}

module.exports={createTransaction}