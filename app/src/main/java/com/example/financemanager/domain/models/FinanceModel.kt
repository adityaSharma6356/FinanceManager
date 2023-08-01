package com.example.financemanager.domain.models

/*
    If this model is changed then changes will be required at:
        data > mappers
        data > local > entities
 */
data class TransactionModel(
    var timeOfTransaction: Long,
    var wasDebit: Boolean,
    var wasCredit: Boolean,
    var amount: Long,
    var title: String,
    var about: String,
    var dayInfo: String
)

data class CalculatedData(
    val CurrentBalance: Long,
    val numberOfCredits: Int,
    val numberOfDebits:Long
)