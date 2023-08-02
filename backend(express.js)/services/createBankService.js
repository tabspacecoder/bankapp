const { where } = require('sequelize');
const db= require('../utils/connectionUtil')
const Banks = db.bank;

const createBank = async (req,res)=>{
    let info = {
        name:req.query.name,
    }

    const bank=await Banks.create(info);
    res.status(200).send(bank);

}

module.exports={createBank}