const { where } = require('sequelize');
const db= require('../utils/connectionUtil');
const Customers = db.customer;
 const loginService=async (req,res)=>{
    let ids=req.body.id;
    let customer=await Customers.findOne({where:{id:ids}});
    let password=req.body.password;
    if(customer==null){

        res.status(400).send({'message':'Customer not found!'});
    }
    else{
        if(customer.password===password){
            res.status(200).send({'message':'Login Successful!'});
        }
        else{
            res.status(400).send({'message':'Login Failed!'});
        }
    }
 }

 module.exports=loginService;