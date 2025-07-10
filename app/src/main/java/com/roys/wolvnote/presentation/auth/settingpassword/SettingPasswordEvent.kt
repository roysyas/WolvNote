package com.roys.wolvnote.presentation.auth.settingpassword

sealed interface SettingPasswordEvent {
    data object InsertPassword: SettingPasswordEvent
    data class PasswordUpdate(val password: String): SettingPasswordEvent
    data class HintUpdate(val hint: String): SettingPasswordEvent
}