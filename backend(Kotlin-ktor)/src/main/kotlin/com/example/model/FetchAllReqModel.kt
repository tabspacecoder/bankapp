package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class FetchAllReqModel(val bank:BankModel,val customer:CustomerModel,val accounts:List<AccountModel>,val transactions:List<TransactionModel>)
