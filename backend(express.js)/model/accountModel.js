const {Sequelize} = require("sequelize");

module.exports=(sequelize,Datatypes,bank,customer)=>{
    const account=sequelize.define("account",{
        id:{
            type:Datatypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        balance:{
            type:Datatypes.FLOAT
        },
        customerId:{
            type:Datatypes.INTEGER,
            references:{
                model: customer,
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
        }
    },{timestamps: false})
    return account;
}