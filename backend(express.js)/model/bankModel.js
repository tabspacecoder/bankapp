
module.exports=(sequelize,Datatypes)=>{
    const bank=sequelize.define("bank",{
        id:{
            type:Datatypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        name:{
            type:Datatypes.STRING
        }
    },{timestamps: false})
    return bank;
}