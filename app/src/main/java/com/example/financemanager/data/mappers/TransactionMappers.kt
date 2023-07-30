package com.example.financemanager.data.mappers

import com.example.financemanager.data.local.TransactionEntity
import com.example.financemanager.domain.models.TransactionModel

fun TransactionModel.toTransactionEntity(): TransactionEntity{
    return TransactionEntity(
        timeOfTransaction = timeOfTransaction,
        wasCredit = wasCredit,
        wasDebit = wasDebit,
        amount = amount
    )
}

fun TransactionEntity.toTransactionModel(): TransactionModel{
    return TransactionModel(
        timeOfTransaction = timeOfTransaction,
        wasCredit = wasCredit,
        wasDebit = wasDebit,
        amount = amount
    )
}