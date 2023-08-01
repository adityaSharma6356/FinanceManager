package com.example.financemanager.presentation.finance.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financemanager.presentation.finance.viewmodels.MainViewModel

@Composable
fun ManageFinance(mainViewModel: MainViewModel){
    // Do your thing here
    Box(modifier = Modifier.fillMaxSize()) {
        FinanceList(mainViewModel)
        BottomButtons(
            mainViewModel = mainViewModel,
            modifier = Modifier
                .padding(bottom = 60.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        )
    }
    if(mainViewModel.state.newTransactionScreenVisible){
        NewTransaction(mainViewModel)
    }
}








