package com.example.financemanager.domain.repositories

import com.example.financemanager.domain.models.CalculatedData
import com.example.financemanager.domain.models.TransactionModel

interface FinancesRepository {

    suspend fun deleteTransactionById(date: Long)

    suspend fun deleteAllFinances()

    suspend fun getAllFinances() : List<TransactionModel>

    suspend fun saveAllFinances(
        data: List<TransactionModel>
    )

    suspend fun saveCalculatedData(
        data: CalculatedData
    )

    suspend fun getCalculatedData(): CalculatedData
}