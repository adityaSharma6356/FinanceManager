package com.example.financemanager.presentation.finance.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financemanager.R
import com.example.financemanager.presentation.finance.util.UiEvents
import com.example.financemanager.presentation.finance.viewmodels.MainViewModel
import com.example.financemanager.ui.theme.customFont

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
//    AnimatedVisibility(
//        enter = fadeIn(tween(200)),
//        exit = fadeOut(tween(200)),
//        modifier = Modifier
//            .zIndex(10f)
//            .padding(top = 110.dp)
//            .fillMaxWidth(),
//        visible = mainViewModel.state.menuOpen) {
//        Column(modifier = Modifier.fillMaxWidth().background(Color.Black), horizontalAlignment = Alignment.CenterHorizontally) {
//            Box(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth().background(Color.White, RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
//                Text(
//                    text = "Manage Finances",
//                    fontFamily = customFont,
//                    fontWeight = FontWeight.Light,
//                    fontSize = 20.sp,
//                    color = Color(0, 0, 0, 134),
//                )
//            }
//            Box(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth().background(Color.White, RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
//                Text(
//                    text = "Financial Advisor",
//                    fontFamily = customFont,
//                    fontWeight = FontWeight.Light,
//                    fontSize = 20.sp,
//                    color = Color(0, 0, 0, 134),
//                )
//            }
//            Box(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth().background(Color.White, RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
//                Text(
//                    text = "Read and Discover",
//                    fontFamily = customFont,
//                    fontWeight = FontWeight.Light,
//                    fontSize = 20.sp,
//                    color = Color(0, 0, 0, 134),
//                )
//            }
//        }
//    }
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp),
        modifier = Modifier
            .padding(top = 40.dp, bottom = 0.dp)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                onClick = { mainViewModel.onEvent(UiEvents.MenuClick) }
            ) {
                Icon(
                    tint = Color(0, 0, 0, 134),
                    painter = painterResource(id = R.drawable.menu_icon),
                    contentDescription = "Menu",
                    )
            }
            Text(
                text = mainViewModel.state.tabName,
                fontFamily = customFont,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                color = Color(0, 0, 0, 134),
                modifier = Modifier
                    .padding(end = 20.dp, top = 20.dp)
            )
        }
        ManageFinance(mainViewModel)
    }
}