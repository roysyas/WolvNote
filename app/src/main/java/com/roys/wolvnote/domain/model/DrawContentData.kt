package com.roys.wolvnote.domain.model

import androidx.compose.ui.unit.IntSize
import com.roys.wolvnote.common.IntSizeSerializer
import kotlinx.serialization.Serializable

@Serializable
data class DrawContentData(
    val pathDataList: List<PathData> = emptyList(),
    @Serializable(with = IntSizeSerializer::class)
    val size: IntSize? = null,
)
