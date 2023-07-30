package com.example.financemanager.domain.repositories

import com.example.financemanager.domain.models.TransactionModel

interface FinancesRepository {

    suspend fun deleteAllFinances()

    suspend fun getAllFinances() : List<TransactionModel>

    suspend fun saveAllFinances(
        data: List<TransactionModel>
    )
}