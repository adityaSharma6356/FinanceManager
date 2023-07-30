package com.example.financemanager.presentation.finance.util

import androidx.compose.ui.graphics.Color
import com.example.financemanager.domain.models.TransactionModel

data class UiStates(
    var finances : MutableList<TransactionModel> = mutableListOf(),
    var isDataLoading :Boolean = true,
    var tabPosition: TabPositions = TabPositions.FINANCES,
    var colorByTab: Color = Color(244, 182, 194, 255),
    var tabName: String = "Manage Finances",
    var menuOpen: Boolean = false
)
