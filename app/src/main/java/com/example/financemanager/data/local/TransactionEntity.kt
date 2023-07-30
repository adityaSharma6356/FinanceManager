package com.example.financemanager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey val id : Int?  = null,
    val timeOfTransaction: Long,
    val wasDebit: Boolean,
    val wasCredit: Boolean,
    val amount: Long
)
