const express = require('express');
const cors = require('cors');

const app = express();

// var options={
//     origin:'localhost:3000'
// }

app.use(cors())

app.use(express.json());
app.use('/api/account', require('./routes/accountRoute'));
app.use('/api/customer', require('./routes/customerRoute'));
app.use('/api/transaction', require('./routes/transactionRoute'));
app.use('/api/bank', require('./routes/bankRoute'));
app.listen(8080);