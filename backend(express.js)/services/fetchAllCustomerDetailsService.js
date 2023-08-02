const db= require('../utils/connectionUtil')
const Transactions = db.transaction;
const Customers = db.customer;
const Banks = db.bank;
const Accounts = db.account;
const fetchAllCustomerDetails = async (req,res)=>{
    let customer=await Customers.findOne({where:{id:req.body.customerId}});
    if(customer==null){
        res.status(400).send({'message':'Customer not found'});
    }
    else{
        if(customer.password==req.body.password){
            let accounts=await Accounts.findAll({where:{customerId:req.body.customerId}});
            let transactions=await Transactions.findAll({where:{customerId:req.body.customerId}});
            let bank=await Banks.findOne({where:{id:customer.bankId}});

            let ret = {
                bank,
               customer,
               accounts,
               transactions
            };
            
            res.status(200).send(ret);
        }
        else{
            res.status(400).send({'message':'Password Incorrect'})
        }
    }

    
}

module.exports={fetchAllCustomerDetails};