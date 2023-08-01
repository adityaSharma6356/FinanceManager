package com.example.financemanager.presentation.finance.util

import androidx.compose.ui.graphics.Color
import com.example.financemanager.domain.models.TransactionModel

data class UiStates(
    var finances : MutableList<TransactionModel> = mutableListOf(),
    var isDataLoading :Boolean = true,
    var tabPosition: Constants = Constants.FINANCES,
    var colorByTab: Color = Color(244, 182, 194, 255),
    var tabName: String = "Manage Finances",
    var menuOpen: Boolean = false,
    var newTransactionScreenVisible: Boolean = false,
    var currentTransactionIs: Transaction = Transaction.CREDIT
)
