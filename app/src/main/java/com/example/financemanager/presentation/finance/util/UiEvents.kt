package com.example.financemanager.presentation.finance.util

sealed class UiEvents {
    data class ChangeTabTo(val positions: TabPositions) : UiEvents()

    object OpenMenu: UiEvents()

    object CloseMenu: UiEvents()

    object MenuClick: UiEvents()


}