package com.roys.wolvnote.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.roys.wolvnote.common.ColorSerializer
import com.roys.wolvnote.common.ListOffsetSerializer
import kotlinx.serialization.Serializable

@Serializable
data class PathData (
    val id: String,
    @Serializable(with = ColorSerializer::class)
    val color: Color,
    val weight: Float,
    @Serializable(with = ListOffsetSerializer::class)
    val path: List<Offset>
)