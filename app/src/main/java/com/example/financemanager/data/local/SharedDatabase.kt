package com.example.financemanager.data.local

import android.content.Context
import com.example.financemanager.domain.models.CalculatedData

class SharedDatabase(val context: Context): SharedDao {
    override suspend fun saveCalculatedData(data: CalculatedData) {
        TODO("Not yet implemented")
    }

    override suspend fun getCalculatedData(): CalculatedData {
        TODO("Not yet implemented")
    }
}