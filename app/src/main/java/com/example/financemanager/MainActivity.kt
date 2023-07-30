package com.example.financemanager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.financemanager.presentation.finance.composables.MainScreen
import com.example.financemanager.presentation.finance.viewmodels.MainViewModel
import com.example.financemanager.ui.theme.FinanceManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceManagerTheme {
                val animatedColorByTab by animateColorAsState(targetValue = mainViewModel.state.colorByTab)
                Card(modifier = Modifier.fillMaxSize(), colors = CardDefaults.cardColors(containerColor = animatedColorByTab)) {
                    MainScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}
