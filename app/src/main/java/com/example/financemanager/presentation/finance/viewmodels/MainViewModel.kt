package com.example.financemanager.presentation.finance.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.domain.repositories.FinancesRepository
import com.example.financemanager.presentation.finance.util.TabPositions
import com.example.financemanager.presentation.finance.util.UiEvents
import com.example.financemanager.presentation.finance.util.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val financesRepository: FinancesRepository
) : ViewModel() {

    var state by mutableStateOf(UiStates())

    init {
//        loadFinances()
    }

    fun onEvent(event: UiEvents){
        when(event){
            is UiEvents.MenuClick -> {
                state = state.copy(menuOpen = !state.menuOpen)
            }
            is UiEvents.OpenMenu -> {
                state = state.copy(menuOpen = true)
            }
            is UiEvents.CloseMenu -> {
                state = state.copy(menuOpen = false)
            }
            is UiEvents.ChangeTabTo -> {
                switchTabTo(event.positions)
            }
            else -> Unit
        }
    }

    private fun loadFinances(){
        viewModelScope.launch {
            Log.d("financeLog", "Loading finance data from local storage")
            state = state.copy(finances = financesRepository.getAllFinances().toMutableList())
            state.isDataLoading = false
        }
    }

    private fun switchTabTo(position: TabPositions){
        when(position){
            TabPositions.FINANCES -> {
                state = state.copy(
                    tabPosition = TabPositions.FINANCES,
                    colorByTab = Color(244, 182, 194, 255),
                    tabName = "Manage Finances",
                )
            }
            TabPositions.HELPER_AI -> {
                state = state.copy(
                    tabPosition = TabPositions.HELPER_AI,
                    colorByTab = Color(135, 223, 233, 255),
                    tabName = "Financial Advisor" // could use Financial Wizard
                )
            }
            TabPositions.NEWS -> {
                state = state.copy(
                    tabPosition = TabPositions.NEWS,
                    colorByTab = Color(116, 125, 147, 255),
                    tabName = "Read and Discover"
                )
            }
        }
    }
}








