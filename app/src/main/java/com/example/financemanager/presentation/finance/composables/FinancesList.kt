package com.example.financemanager.presentation.finance.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.presentation.finance.util.UiEvents
import com.example.financemanager.presentation.finance.viewmodels.MainViewModel
import com.example.financemanager.ui.theme.customFont

@Composable
fun FinanceList(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {

        FinanceGraph(mainViewModel = mainViewModel)

        LazyColumn(contentPadding = PaddingValues(bottom = 120.dp)) {
            items(
                mainViewModel.state.finances.size,
                key = {
                    mainViewModel.state.finances[it].timeOfTransaction
            }){
                val contextColor by remember {
                    mutableStateOf(
                        if(mainViewModel.state.finances[it].wasCredit){
                            Color(0, 255, 19, 50)
                        } else {
                            Color(255, 13, 0, 50)
                        }
                    )
                }
                val sighn by remember {
                    mutableStateOf(
                        if(mainViewModel.state.finances[it].wasCredit){
                            "+"
                        } else {
                            "-"
                        }
                    )
                }
                var targetState by remember { mutableStateOf(false) }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            targetState = !targetState
                        }
                        .background(contextColor, RoundedCornerShape(10.dp))) {

                    Box(Modifier.weight(1f)) {
                        Crossfade(
                            modifier = Modifier
                                .padding(end = 15.dp, start = 10.dp)
                                .fillMaxWidth(),
                            targetState = targetState) { show ->
                            if(!show){
                                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                                    Text(
                                        text = mainViewModel.state.finances[it].title,
                                        fontFamily = customFont,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 17.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color(0, 0, 0, 196),
                                    )
                                    Text(
                                        text = mainViewModel.state.finances[it].dayInfo,
                                        fontFamily = customFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color(0, 0, 0, 94),
                                    )
                                }
                            } else {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start,modifier = Modifier.fillMaxSize()) {
                                    IconButton(onClick = { mainViewModel.onEvent(UiEvents.DeleteTransactionByDate(mainViewModel.state.finances[it].timeOfTransaction)) }, modifier = Modifier.padding(20.dp ,0.dp)) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "Delete",
                                            tint = Color(0, 0, 0, 171)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Text(
                        text = sighn+" ₹"+mainViewModel.state.finances[it].amount.toString(),
                        fontFamily = customFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color(0, 0, 0, 196),
                        modifier = Modifier
                            .padding(end = 15.dp)
                    )
                }
            }
        }
    }
}