const express = require('express');

const { fetchAccountMethod,createAccountMethod,depositAmoutMethod,withdrawAmoutMethod,transferAmountMethod } = require('../controllers/accountController.js');

const accountRouter = express.Router();

accountRouter.route('/fetch-accounts').post(fetchAccountMethod);
accountRouter.route('/create-account').post(createAccountMethod);
accountRouter.route('/deposit-amount').post(depositAmoutMethod);
accountRouter.route('/withdraw-amount').post(withdrawAmoutMethod);
accountRouter.route('/transfer-amount').post(transferAmountMethod);




module.exports = accountRouter;