package com.roys.wolvnote.presentation.note.draw.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.roys.wolvnote.R
import com.roys.wolvnote.presentation.note.component.IconTextButton
import com.roys.wolvnote.presentation.note.component.TextButton
import com.roys.wolvnote.presentation.note.draw.DrawEvent
import com.roys.wolvnote.presentation.ui.composableicon.CleanIcon
import com.roys.wolvnote.presentation.ui.composableicon.GalleryIcon
import com.roys.wolvnote.presentation.ui.composableicon.SaveIcon

@Composable
fun DrawMenu(
    selectedColor: Color,
    colors: List<Color>,
    selectedWeight: Float,
    weights: List<Float>,
    onClick: (DrawEvent) -> Unit,
    onSelectColor: (Color) -> Unit,
    onSelectWeight: (Float) -> Unit
){
    val clear = stringResource(R.string.clear)
    val save = stringResource(R.string.save)
    val saveExport = stringResource(R.string.save_and_export)

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column {
            SelectWeightComponent(
                selectedWeight = selectedWeight,
                weights = weights,
                onSelectWeight = {onSelectWeight(it)}
            )
            SelectColorComponent(
                colors = colors,
                selectedColor = selectedColor,
                onSelectColor = {onSelectColor(it)}
            )
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                IconTextButton(
                    { onClick(DrawEvent.OnClearCanvasClick) },
                    clear,
                    CleanIcon()
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 6.dp),
                    onClick = {onClick(DrawEvent.InsertNote)}
                ) {
                    Icon(
                        imageVector = SaveIcon(),
                        contentDescription = save,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                TextButton(
                    imageVector = GalleryIcon(),
                    label = saveExport,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onClick = {onClick(DrawEvent.InsertAndExport)}
                )
            }
        }
    }
}