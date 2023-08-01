package com.example.financemanager.data.local

import com.example.financemanager.domain.models.CalculatedData

interface SharedDao {

    suspend fun saveCalculatedData(
        data: CalculatedData
    )

    suspend fun getCalculatedData(): CalculatedData
}