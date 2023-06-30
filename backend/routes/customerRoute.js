const express = require('express');

const { loginCustomer,createCustomerMethod,fetchAllCustomerDetailsMethod } = require('../controllers/customerController');

const customerRouter = express.Router();

customerRouter.route('/login').post(loginCustomer);
customerRouter.route('/create-user').post(createCustomerMethod);
customerRouter.route('/fetch-all-details').post(fetchAllCustomerDetailsMethod)


module.exports = customerRouter;