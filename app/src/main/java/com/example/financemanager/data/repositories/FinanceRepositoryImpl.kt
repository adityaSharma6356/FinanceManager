package com.example.financemanager.data.repositories

import com.example.financemanager.data.local.FinanceDatabase
import com.example.financemanager.data.local.SharedDao
import com.example.financemanager.data.mappers.toTransactionEntity
import com.example.financemanager.data.mappers.toTransactionModel
import com.example.financemanager.domain.models.CalculatedData
import com.example.financemanager.domain.models.TransactionModel
import com.example.financemanager.domain.repositories.FinancesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinanceRepositoryImpl @Inject constructor(
    private val financeDatabase: FinanceDatabase,
    private val sharedDao: SharedDao
) : FinancesRepository {

    private val dao = financeDatabase.dao

    override suspend fun deleteAllFinances() {
        dao.deleteFinanceData()
    }

    override suspend fun getAllFinances(): List<TransactionModel> {
        return dao.getAllFinances().map { it.toTransactionModel() }
    }

    override suspend fun saveAllFinances(data: List<TransactionModel>) {
        dao.saveFinanceData(data.map { it.toTransactionEntity() })
    }

    override suspend fun deleteTransactionById(date: Long) {
        dao.deleteByUserId(date)
    }

    override suspend fun saveCalculatedData(data: CalculatedData) {
        sharedDao.saveCalculatedData(data)
    }

    override suspend fun getCalculatedData(): CalculatedData {
        return sharedDao.getCalculatedData()
    }
}













