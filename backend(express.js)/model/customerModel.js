
const {Sequelize} = require("sequelize");
module.exports=(sequelize,Datatypes,bank)=>{
    const customer=sequelize.define("customer",{
        id:{
            type:Datatypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        name:{
            type:Datatypes.STRING,
        },
        email:{
            type:Datatypes.STRING,
        },
        password:{
            type:Datatypes.STRING,
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
    return customer;
}