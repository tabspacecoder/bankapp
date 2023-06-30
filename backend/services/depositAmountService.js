const db= require('../utils/connectionUtil');
const Customers = db.customer;
const Accounts=db.account;
const Transactions=db.transaction;
 const depositAmount=async (req,res)=>{
    let cusId=req.body.customerId;
    let customer=await Customers.findOne({where:{id:cusId}});
    if(customer==null){
        res.status(400).send({'mesage':'Customer not found!'});
    }
    else{
        if(customer.password==req.body.password){
            let account = await Accounts.findOne({where:{id:req.body.accountId}});
            if(account==null){
                res.status(400).send({'mesage':'account not found'});
            }
            else{
                account.balance=parseFloat(account.balance)+parseFloat(req.body.amount);
                console.log(account);
                let finalacc=await Accounts.update(account.dataValues,{where:{id:req.body.accountId}});
                let info = {
                    amount:req.body.amount,
                    type:"CREDIT",
                    accountId:req.body.accountId,
                    bankId:customer.bankId,
                    customerId:customer.id
                }
            
                const tranaction=await Transactions.create(info);
                res.status(200).send(account.dataValues);
                   
            }
    
        }
        else{
            res.status(400).send({'message':'Password incorrect!'});
        }
    }

    
 }

 module.exports={depositAmount};