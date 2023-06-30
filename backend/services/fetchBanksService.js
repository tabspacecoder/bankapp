const { where } = require('sequelize');
const db= require('../utils/connectionUtil')
const Banks = db.bank;

const fetchBanks = async (req,res)=>{
let banks=await Banks.findAll({});
res.status(200).send(banks);
}

module.exports={fetchBanks}