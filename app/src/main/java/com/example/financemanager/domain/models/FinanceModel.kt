package com.example.financemanager.domain.models

/*
    If this model is changed then changes will be required at:
        data > mappers
        data > local > entities
 */
data class TransactionModel(
    val timeOfTransaction: Long,
    val wasDebit: Boolean,
    val wasCredit: Boolean,
    val amount: Long
)