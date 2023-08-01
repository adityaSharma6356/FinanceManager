package com.example.financemanager.presentation.finance.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.financemanager.R
import com.example.financemanager.domain.models.TransactionModel
import com.example.financemanager.presentation.finance.util.Transaction
import com.example.financemanager.presentation.finance.util.UiEvents
import com.example.financemanager.presentation.finance.viewmodels.MainViewModel
import com.example.financemanager.ui.theme.customFont
import java.sql.Timestamp
import java.util.Date

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewTransaction(mainViewModel: MainViewModel) {
    var amount by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }
    var tst by remember { mutableStateOf(Timestamp(Date().time)) }
    var amountFilled by remember { mutableStateOf(true) }
    var titleFilled by remember { mutableStateOf(true) }
    var needAboutBlock by remember { mutableStateOf(false) }
    val contextColor by remember {
        mutableStateOf(
            if(mainViewModel.state.currentTransactionIs==Transaction.CREDIT){
                Color(108, 188, 114, 255)
            } else {
                Color(247, 102, 94, 255)
            }
        )
    }
    var tempWidth by remember {
        mutableStateOf(2.dp)
    }
    val animatedWidth by animateDpAsState(
        animationSpec =  tween(100, easing = LinearEasing),
        targetValue = tempWidth,
        finishedListener = { tempWidth = 2.dp })
    AlertDialog(
        containerColor = Color.White,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(400.dp)
            .border(animatedWidth, contextColor, RoundedCornerShape(25.dp)),
        shape = RoundedCornerShape(25.dp),
        onDismissRequest = {
                           tempWidth = 10.dp
        },
        text = {
            val pattern = remember { Regex("^\\d+\$") }
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = tst.toString(),
                    fontFamily = customFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 5.dp)
                )
                OutlinedTextField(
                    trailingIcon = {
                        if(!amountFilled && amount.isBlank()){
                            Icon(painter = painterResource(id = R.drawable.empty_field_icon), contentDescription = null, tint = Color.Red)
                        }
                    },
                    leadingIcon = {
                                  Text(text = "Rs", fontSize = 15.sp)
                    },
                    colors = TextFieldDefaults
                        .outlinedTextFieldColors(
                            textColor = Color.Black,
                            unfocusedBorderColor = if(amount.isEmpty()) Color.Gray else contextColor,
                            focusedBorderColor = contextColor,
                            focusedLabelColor = contextColor,
                            cursorColor = contextColor,
                            unfocusedLabelColor = if(amount.isBlank()) Color(0, 0, 0, 72) else contextColor
                        ),
                    shape = RoundedCornerShape(10.dp),
                    value = amount,
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = customFont, fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        if(it.isEmpty() || it.matches(pattern) || it.isBlank()){
                            amount = it
                        }
                    },
                    label = {
                        Text(
                            text = "Amount"
                        )
                    }
                )
                OutlinedTextField(
                    trailingIcon = {
                        if(!titleFilled && title.isBlank()){
                            Icon(painter = painterResource(id = R.drawable.empty_field_icon), contentDescription = null, tint = Color.Red)
                        }
                    },
                    colors = TextFieldDefaults
                        .outlinedTextFieldColors(
                            textColor = Color.Black,
                            unfocusedBorderColor = Color.Gray,
                            focusedBorderColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            cursorColor = Color.Gray,
                        ),
                    shape = RoundedCornerShape(10.dp),
                    value = title,
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = customFont, fontSize = 15.sp),
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text(
                            text = "Title"
                        )
                    }
                )
                if(needAboutBlock){
                    OutlinedTextField(
                        colors = TextFieldDefaults
                            .outlinedTextFieldColors(
                                textColor = Color.Black,
                                unfocusedBorderColor = Color.Gray,
                                focusedBorderColor = Color.Black,
                                focusedLabelColor = Color.Black,
                                cursorColor = Color.Gray,
                            ),
                        shape = RoundedCornerShape(10.dp),
                        value = about,
                        textStyle = TextStyle(fontFamily = customFont, fontSize = 15.sp),
                        onValueChange = {
                            about = it
                        },
                        label = {
                            Text(
                                text = "Note"
                            )
                        }
                    )
                } else {
                    Text(
                        text = "add a note",
                        fontFamily = customFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color(91, 191, 247, 255),
                        modifier = Modifier.padding(start = 10.dp, top = 20.dp).clickable {
                            needAboutBlock = true
                        }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = contextColor),
                onClick = {
                    amountFilled = amount.isNotBlank()
                    titleFilled = title.isNotBlank()
                    if(amount.isNotBlank() && title.isNotBlank()){
                        val tempFinance = TransactionModel(
                            timeOfTransaction = tst.time,
                            wasDebit = mainViewModel.state.currentTransactionIs==Transaction.DEBIT,
                            about = about,
                            title = title,
                            amount = amount.toLong(),
                            wasCredit = mainViewModel.state.currentTransactionIs==Transaction.CREDIT,
                            dayInfo = ""
                        )
                        mainViewModel.onEvent(UiEvents.SaveTransaction(tempFinance))
                    }
                }) {
                Text(
                    text = "CONFIRM",
                    fontFamily = customFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(0, 0, 0, 196)
                )
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = { mainViewModel.onEvent(UiEvents.CloseTransactionRequest) }) {
                Text(
                    text = "CANCEL",
                    fontFamily = customFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(0, 0, 0, 171)
                )
            }
        }
    )
}