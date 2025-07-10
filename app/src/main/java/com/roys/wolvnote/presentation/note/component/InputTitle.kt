package com.roys.wolvnote.presentation.note.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.roys.wolvnote.R
import com.roys.wolvnote.presentation.ui.composableicon.SendIcon

@Composable
fun BoxScope.InputTitle(
    onClick: (String)-> Unit
) {
    val titleText = stringResource(R.string.input_title)
    var noteTitle by remember { mutableStateOf("") }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .align(Alignment.BottomStart),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ){
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = noteTitle,
                onValueChange = {
                    noteTitle = it
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = titleText,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
            )
            IconButton(
                onClick = {
                    onClick(noteTitle)
                }
            ) {
                Icon(
                    imageVector = SendIcon(),
                    contentDescription = titleText,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}