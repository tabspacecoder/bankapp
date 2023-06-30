const db= require('../utils/connectionUtil');
const Customers = db.customer;
const Accounts=db.account;
const Transactions=db.transaction;
 const transferAmount=async (req,res)=>{
    let cusId=req.body.customerId;
    let customer=await Customers.findOne({where:{id:cusId}});
    if(customer==null){
        res.status(400).send({'message':'Customer not found!'});
    }
    else{
        if(customer.password==req.body.password){
            let withdrawAccount = await Accounts.findOne({where:{id:req.body.withdrawAccountId}});
            let depositAccount = await Accounts.findOne({where:{id:req.body.depositAccountId}});
            if(withdrawAccount==null){
                res.status(400).send({'message':'Withdraw account not found'});
            }
            else if(depositAccount==null){
                res.status(400).send({'message':'Account to deposit not found'});
            }
            else{
                withdrawAccount.balance=parseFloat(withdrawAccount.balance)-parseFloat(req.body.amount);
                if(withdrawAccount.balance<0){
                    res.status(400).send({'message':'Insufficient Funds'});
                }
                else{
                    let finalacc=await Accounts.update(withdrawAccount.dataValues,{where:{id:req.body.withdrawAccountId}});
                    depositAccount.balance=parseFloat(depositAccount.balance)+parseFloat(req.body.amount);
                    let finalacc1=await Accounts.update(depositAccount.dataValues,{where:{id:req.body.depositAccountId}});
                    let info = {
                        amount:req.body.amount,
                        type:"DEBIT",
                        accountId:req.body.withdrawAccountId,
                        bankId:customer.bankId,
                        customerId:customer.id
                    }
                
                    const tranaction=await Transactions.create(info);
                    let info1 = {
                        amount:req.body.amount,
                        type:"CREDIT",
                        accountId:depositAccount.id,
                        bankId:depositAccount.bankId,
                        customerId:depositAccount.customerId
                    }
                
                    const tranaction1=await Transactions.create(info1);
                    res.status(200).send({'message':'Transfer successful!'});
                }

            }
    
        }
        else{
            res.status(400).send({'message':'Password incorrect!'});
        }
    }

    
 }

 module.exports={transferAmount};