package com.example.financemanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [TransactionEntity::class],
    version = 1
)
abstract class FinanceDatabase: RoomDatabase() {
    abstract val dao: FinanceDao
}