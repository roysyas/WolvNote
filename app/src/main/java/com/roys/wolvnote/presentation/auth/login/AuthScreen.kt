package com.roys.wolvnote.presentation.auth.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.roys.wolvnote.presentation.ui.util.Screen

@Composable
fun AuthScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if(state.isEmpty){
        navController.navigate(Screen.SettingPasswordScreen.route){
            popUpTo(Screen.AuthScreen.route){
                inclusive = true
            }
        }
    }else{
        LoginComponent(
            paddingValues = paddingValues,
            passwordInput = state.passwordInputText,
            hint = state.hint,
            navController = navController,
            isSuccess = state.isSuccess,
            errorText = state.errorText?.asString(),
            onClick = {
                viewModel.handleEvent(LoginEvent.CheckPassword)
            },
            onChange = {
                viewModel.handleEvent(LoginEvent.PasswordUpdate(it))
            }
        )
    }
}