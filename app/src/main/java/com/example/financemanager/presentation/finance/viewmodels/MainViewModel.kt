package com.example.financemanager.presentation.finance.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.domain.models.TransactionModel
import com.example.financemanager.domain.repositories.FinancesRepository
import com.example.financemanager.presentation.finance.util.Constants
import com.example.financemanager.presentation.finance.util.UiEvents
import com.example.financemanager.presentation.finance.util.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val financesRepository: FinancesRepository
) : ViewModel() {

    var state by mutableStateOf(UiStates())

    init {
        loadFinances()
    }

    fun onEvent(event: UiEvents){
        when(event){
            is UiEvents.DeleteTransactionByDate -> {
                deleteById(event.date)
            }
            is UiEvents.SaveTransaction -> {
                state = state.copy(
                    newTransactionScreenVisible = false
                )
                saveTransaction(event.data)
            }
            is UiEvents.CloseTransactionRequest -> {
                state = state.copy(newTransactionScreenVisible = false)
            }
            is UiEvents.NewTransaction -> {
                state = state
                    .copy(
                        newTransactionScreenVisible = true,
                        currentTransactionIs = event.transactionWas
                    )
            }
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

    fun getDayInfo(timestamp: Long): String{
        val currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()
        // Convert the given timestamp to a LocalDate object
        return when (Instant.ofEpochSecond(timestamp/1000).atZone(ZoneId.systemDefault()).toLocalDate()) {
            currentDateTime -> {
                "Today"
            }
            currentDateTime.minusDays(1) -> {
                "Yesterday"
            }
            else -> {
                val dateTime = Instant.ofEpochSecond(timestamp/1000).atZone(ZoneId.systemDefault())
                // Define the desired format
                val formatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
                // Format the date and time using the defined formatter
                dateTime.format(formatter)
            }
        }
    }
    private fun saveTransaction(data: TransactionModel){
        viewModelScope.launch {
            Log.d("financeLog", "Saving Transaction to local storage")
            val tempData : List<TransactionModel> = listOf(data)
            financesRepository.saveAllFinances(tempData)
            loadFinances()
        }
    }

    private fun loadFinances(){
        viewModelScope.launch {
            state.isDataLoading = true
            Log.d("financeLog", "Loading finance data from local storage")
            var tempData = financesRepository.getAllFinances()
            tempData.forEach {
                it.dayInfo = getDayInfo(it.timeOfTransaction)
            }
            state = state.copy(finances = tempData.toMutableList())
            state.isDataLoading = false
        }
    }

    private fun deleteById(date: Long){
        viewModelScope.launch(Dispatchers.IO) {
            financesRepository.deleteTransactionById(date)
            loadFinances()
        }
    }

    private fun switchTabTo(position: Constants){
        when(position){
            Constants.FINANCES -> {
                state = state.copy(
                    tabPosition = Constants.FINANCES,
                    colorByTab = Color(244, 182, 194, 255),
                    tabName = "Manage Finances",
                )
            }
            Constants.HELPER_AI -> {
                state = state.copy(
                    tabPosition = Constants.HELPER_AI,
                    colorByTab = Color(135, 223, 233, 255),
                    tabName = "Financial Advisor" // could use Financial Wizard
                )
            }
            Constants.NEWS -> {
                state = state.copy(
                    tabPosition = Constants.NEWS,
                    colorByTab = Color(116, 125, 147, 255),
                    tabName = "Read and Discover"
                )
            }
        }
    }
}








