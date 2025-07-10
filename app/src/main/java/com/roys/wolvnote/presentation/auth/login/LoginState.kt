package com.roys.wolvnote.presentation.auth.login

import com.roys.wolvnote.common.UiText

data class LoginState(
    val isLoading: Boolean = false,
    val errorText: UiText? = null,
    val isEmpty: Boolean = false,
    val hint: String = "",
    val password: String? = null,
    val isSuccess: Boolean = false,
    val passwordInputText: String = ""
)