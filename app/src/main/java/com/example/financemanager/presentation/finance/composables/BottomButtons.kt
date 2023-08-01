package com.example.financemanager.presentation.finance.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.presentation.finance.util.Transaction
import com.example.financemanager.presentation.finance.util.UiEvents
import com.example.financemanager.presentation.finance.viewmodels.MainViewModel
import com.example.financemanager.ui.theme.customFont

@Composable
fun BottomButtons(mainViewModel: MainViewModel, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            elevation = ButtonDefaults.buttonElevation(10.dp),
            onClick = { mainViewModel.onEvent(UiEvents.NewTransaction(Transaction.DEBIT)) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(247, 102, 94, 255)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .weight(1f)
                .height(50.dp)
        ) {
            Text(
                text = "- DEBIT",
                fontFamily = customFont,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(0, 0, 0, 206)
            )
        }
        Button(
            elevation = ButtonDefaults.buttonElevation(10.dp),
            onClick = { mainViewModel.onEvent(UiEvents.NewTransaction(Transaction.CREDIT)) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(108, 188, 114, 255)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(20.dp, 0.dp)
                .weight(1f)
                .height(50.dp)
        ) {
            Text(
                text = "+ CREDIT",
                fontFamily = customFont,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(0, 0, 0, 206)
            )
        }
    }
}












