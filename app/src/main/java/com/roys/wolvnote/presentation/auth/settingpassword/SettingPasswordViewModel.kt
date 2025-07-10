package com.roys.wolvnote.presentation.auth.settingpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roys.wolvnote.R
import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.common.UiText
import com.roys.wolvnote.common.encryptData
import com.roys.wolvnote.data.database.PasswordTable
import com.roys.wolvnote.domain.usecase.password.InsertPasswordUseCase
import com.roys.wolvnote.presentation.ui.util.SnackBarController
import com.roys.wolvnote.presentation.ui.util.SnackBarError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingPasswordViewModel @Inject constructor(
    private val insertPasswordUseCase: InsertPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingPasswordsState())
    val state: StateFlow<SettingPasswordsState> = _state.asStateFlow()

    fun handleEvent(viewEvent: SettingPasswordEvent){
        when(viewEvent){
            is SettingPasswordEvent.InsertPassword -> saveCredential()
            is SettingPasswordEvent.HintUpdate -> hintUpdate(viewEvent.hint)
            is SettingPasswordEvent.PasswordUpdate -> passwordUpdate(viewEvent.password)
        }
    }

    private fun passwordUpdate(password: String){
        _state.update {
            it.copy(
                passwordInputText = password
            )
        }
    }

    private fun hintUpdate(hint: String){
        _state.update {
            it.copy(
                hintInputText = hint
            )
        }
    }

    private fun saveCredential(){
        if(_state.value.passwordInputText.isEmpty()){
            _state.update {
                it.copy(
                    errorText = UiText.StringResource(R.string.empty_password)
                )
            }
        }else if(_state.value.hintInputText.isEmpty()){
            _state.update {
                it.copy(
                    errorText = UiText.StringResource(R.string.empty_hint)
                )
            }
        }else{
            prepareInsertPassword(
                _state.value.passwordInputText,
                _state.value.hintInputText
            )
        }
    }

    private fun prepareInsertPassword(password:String, hint: String){
        val passwordTable = PasswordTable(
            passwordId = null,
            password = encryptData(password),
            hint = hint
        )
        insertPassword(passwordTable)
    }

    private fun insertPassword(passwordTable: PasswordTable) {
        insertPasswordUseCase.invoke(passwordTable).onEach { result ->
            when(result){
                is Resource.Loading ->{
                    _state.value = SettingPasswordsState(isLoading = true)
                }
                is Resource.Error -> {
                    viewModelScope.launch {
                        SnackBarController.sendEvent(
                            event = SnackBarError(
                                message = result.message ?: "An unexpected error occurred"
                            )
                        )
                    }
                }
                is Resource.Success -> {
                    _state.value = SettingPasswordsState(isSuccess = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}