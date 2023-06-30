const dbConfig=require('../configs/dbConfig.js');

const {Sequelize,DataTypes} = require("sequelize");
const sequelize = new Sequelize(
 dbConfig.DB,
 dbConfig.USER,
 dbConfig.PASSWORD,
  {
    host: dbConfig.HOST,
    dialect: dbConfig.DIALECT,
    operatorsAliases:false,
    pool:{
      max:dbConfig.pool.max,
      min:dbConfig.pool.min,
      acquire:dbConfig.pool.acquire,
      idle:dbConfig.pool.idle
    }
  }
);
sequelize.authenticate().then(() => {
    console.log('Connection has been established successfully.');
 }).catch((error) => {
    console.error('Unable to connect to the database: ', error);
 });

 const db={};
 db.Sequelize=Sequelize;
 db.sequelize=sequelize;
 db.bank=require('../model/bankModel.js')(sequelize,DataTypes);
 db.customer=require('../model/customerModel.js')(sequelize,DataTypes,db.bank);
 db.account=require('../model/accountModel.js')(sequelize,DataTypes,db.bank,db.customer);
 db.transaction=require('../model/transactionModel.js')(sequelize,DataTypes,db.bank,db.customer,db.account);

 db.sequelize.sync({force:false}).then(()=>{console.log("Yes! re-sync done!")});

 module.exports=db;
