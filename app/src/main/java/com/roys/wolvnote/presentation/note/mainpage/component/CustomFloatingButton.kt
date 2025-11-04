package com.roys.wolvnote.presentation.note.mainpage.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.roys.wolvnote.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.roys.wolvnote.common.Constants
import com.roys.wolvnote.presentation.note.component.FloatingItemButton
import com.roys.wolvnote.presentation.note.mainpage.HomeState
import com.roys.wolvnote.presentation.ui.composableicon.CreateChecklistIcon
import com.roys.wolvnote.presentation.ui.composableicon.CreateDrawIcon
import com.roys.wolvnote.presentation.ui.composableicon.CreateNoteIcon
import com.roys.wolvnote.presentation.ui.composableicon.CreateSalaryIcon

@Composable
fun CustomFloatingButton(
    state: HomeState,
    onClick: (Int) -> Unit,
    onToggle: (Boolean) -> Unit
) {
    val addNotes = stringResource(R.string.add_notes)
    val createChecklist = stringResource(R.string.create_checklist)
    val createNotes = stringResource(R.string.create_notes)
    val createDraw = stringResource(R.string.create_draw)
    val createSalary = stringResource(R.string.create_salary)

    Column(horizontalAlignment = Alignment.End) {
        if (state.isToggle) {
            Column(horizontalAlignment = Alignment.End) {
                FloatingItemButton(
                    label = createNotes,
                    imageVector = CreateNoteIcon(),
                    onClick = {
                        onClick(Constants.CATEGORY_NOTE)
                        onToggle(false)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                FloatingItemButton(
                    label = createChecklist,
                    imageVector = CreateChecklistIcon(),
                    onClick = {
                        onClick(Constants.CATEGORY_CHECKLIST)
                        onToggle(false)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                FloatingItemButton(
                    label = createSalary,
                    imageVector = CreateSalaryIcon(),
                    onClick = {
                        onClick(Constants.CATEGORY_SALARY)
                        onToggle(false)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                FloatingItemButton(
                    label = createDraw,
                    imageVector = CreateDrawIcon(),
                    onClick = {
                        onClick(Constants.CATEGORY_DRAW)
                        onToggle(false)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        FloatingActionButton(
            modifier = Modifier.border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(16.dp)
            ),
            shape = RoundedCornerShape(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                if(state.isToggle){
                    onToggle(false)
                }else{
                    onToggle(true)
                }
            }
        ) {
            Icon(
                painter = if(state.isToggle) painterResource(R.drawable.close) else painterResource(R.drawable.add),
                contentDescription = addNotes,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}