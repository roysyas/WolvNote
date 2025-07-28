package com.roys.wolvnote.presentation.note.salarycalculation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.roys.wolvnote.R
import com.roys.wolvnote.common.digitsFormater
import com.roys.wolvnote.presentation.ui.composableicon.SendIcon

@Composable
fun BoxScope.InputSalary(
    onClick:(String) -> Unit
){
    var salaryAmount by rememberSaveable { mutableStateOf(TextFieldValue("")) }
    val inputSalary = stringResource(R.string.input_salary)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .align(Alignment.BottomStart),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ){
        OutlinedTextField(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            value = salaryAmount,
            onValueChange = {
                val formattedText = digitsFormater(it.text)
                salaryAmount = TextFieldValue(
                    text = formattedText,
                    selection = TextRange(formattedText.length)
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions {
                onClick(salaryAmount.text)
            },
            label = {
                Text(
                    text = inputSalary,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
            trailingIcon = {
                AnimatedVisibility(
                    visible = salaryAmount.text.isNotBlank()
                ) {
                    IconButton(
                        onClick = {
                            onClick(salaryAmount.text)
                        }
                    ) {
                        Icon(
                            imageVector = SendIcon(),
                            contentDescription = inputSalary,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        )
    }
}