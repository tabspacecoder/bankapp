const express = require('express');

const { fetchBanksMethod,createBanksMethod } = require('../controllers/bankController.js');

const bankRouter = express.Router();

bankRouter.route('/fetch-banks').post(fetchBanksMethod);
bankRouter.route('/create-bank').post(createBanksMethod);





module.exports = bankRouter;