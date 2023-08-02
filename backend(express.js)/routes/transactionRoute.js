const express = require('express');

const { fetchTransactionMethod,createTransactionMethod } = require('../controllers/transactionController.js');

const transactionRouter = express.Router();

transactionRouter.route('/fetch-transactions').post(fetchTransactionMethod);
transactionRouter.route('/create-transaction').post(createTransactionMethod);



module.exports = transactionRouter;