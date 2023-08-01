package com.example.financemanager.presentation.finance.util

import com.example.financemanager.domain.models.TransactionModel

sealed class UiEvents {
    data class ChangeTabTo(val positions: Constants) : UiEvents()

    object OpenMenu: UiEvents()

    object CloseMenu: UiEvents()

    object MenuClick: UiEvents()

    data class NewTransaction(val transactionWas: Transaction): UiEvents()

    object CloseTransactionRequest: UiEvents()

    data class SaveTransaction(val data: TransactionModel): UiEvents()

    data class DeleteTransactionByDate(val date: Long): UiEvents()

}