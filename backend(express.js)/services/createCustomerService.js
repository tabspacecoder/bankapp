const { where } = require('sequelize');
const db= require('../utils/connectionUtil')
const Customers = db.customer;
const createCustomer = async (req,res)=>{
    let info = {
        name:req.body.name,
        email:req.body.email,
        password:req.body.password,
        bankId:req.body.bankId

    }

    const customer=await Customers.create(info);
    // Customers.findOne({where:{id:customer.id}}).then(result=> res.status(200).send(result));
    res.status(200).send(customer)
   

}

module.exports={createCustomer}