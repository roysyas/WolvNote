package com.roys.wolvnote.presentation.note.checklist

import com.roys.wolvnote.domain.model.CheckListData

data class CheckListState (
    val checkListData: CheckListData = CheckListData(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isEdit: Boolean = false
)