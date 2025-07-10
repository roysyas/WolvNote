package com.roys.wolvnote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CheckListItem (
    val checked: Boolean,
    val text: String,
    val id: String
)