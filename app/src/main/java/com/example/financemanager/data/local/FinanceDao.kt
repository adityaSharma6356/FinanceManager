package com.example.financemanager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFinanceData(
        finances: List<TransactionEntity>
    )

    @Query(
        """
            DELETE FROM transactionentity
        """
    )
    suspend fun deleteFinanceData()

    @Query(
        """
            SELECT * FROM transactionentity ORDER BY timeOfTransaction DESC
        """
    )
    suspend fun getAllFinances() : List<TransactionEntity>

    @Query("DELETE FROM transactionentity WHERE timeOfTransaction = :time")
    fun deleteByUserId(time: Long)

}