const db= require('../utils/connectionUtil')
const Accounts = db.account;
const Customers=db.customer;
const createAccount = async (req,res)=>{
    let info = {
        balance:req.body.balance,
        customerId:req.body.customerId,
        bankId:req.body.bankId
    }
    let cusId=req.body.customerId;
    let customer=await Customers.findOne({where:{id:cusId}});
    if(customer==null){
        res.status(400).send({'message':'Customer not found!'});
    }
    else{
        if(req.body.password==customer.password){
            const account=await Accounts.create(info);
            res.status(200).send(account);
        }
        else{
            res.status(400).send({'message':'Password Incorrect!'})
        }
        
    }
    

}

module.exports={createAccount}