package com.roys.wolvnote.presentation.note.checklist.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import com.roys.wolvnote.domain.model.CheckListItem
import com.roys.wolvnote.presentation.ui.composableicon.SaveIcon
import com.roys.wolvnote.presentation.ui.composableicon.SendIcon

@Composable
fun AddItemView(
    list: List<CheckListItem>,
    onClick: (String, Boolean) -> Unit,
    insertNoteClick: () -> Unit
) {
    val addItemText = stringResource(R.string.add_item)
    val inputText = stringResource(R.string.input_checklist)
    val save = stringResource(R.string.save)
    var checked by remember { mutableStateOf(false) }
    var itemText by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = itemText,
                onValueChange = {
                    itemText = it
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = inputText,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = itemText.isNotBlank()
                    ) {
                        IconButton(
                            onClick = {
                                onClick(itemText, checked)
                                itemText = ""
                                checked = false
                            }
                        ) {
                            Icon(
                                imageVector = SendIcon(),
                                contentDescription = addItemText,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
            if (list.isNotEmpty()) {
                IconButton(
                    onClick = {
                        insertNoteClick()
                    }
                ) {
                    Icon(
                        imageVector = SaveIcon(),
                        contentDescription = save,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}