const {Sequelize} = require("sequelize");
module.exports=(sequelize,Datatypes,bank,customer,account)=>{
    const transaction=sequelize.define("transaction",{
        id:{
            type:Datatypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        amount:{
            type:Datatypes.DOUBLE,
        },
        type:{
            type:Datatypes.STRING,
        },
        accountId:{
            type:Datatypes.INTEGER,
            references:{
                model: account,
                key: 'id',
                deferrable: Sequelize.Deferrable.INITIALLY_IMMEDIATE
              }
        },
        bankId:{
            type:Datatypes.INTEGER,
            references:{
                model: bank,
                key: 'id',
                deferrable: Sequelize.Deferrable.INITIALLY_IMMEDIATE
              }
        },
        customerId:{
            type:Datatypes.INTEGER,
            references:{
                model: customer,
                key: 'id',
                deferrable: Sequelize.Deferrable.INITIALLY_IMMEDIATE
              }
        },
    },{timestamps: false})
    return transaction;
}