package com.roys.wolvnote.presentation.auth.settingpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.roys.wolvnote.R
import com.roys.wolvnote.presentation.ui.util.Screen

@Composable
fun SettingPasswordScreen(
    navController: NavController,
    viewModel: SettingPasswordViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val createPassword = stringResource(R.string.create_password)
    val createHint = stringResource(R.string.create_hint)
    val submit = stringResource(R.string.submit)

    if(state.isSuccess){
        navController.navigate(Screen.AuthScreen.route){
            popUpTo(Screen.SettingPasswordScreen.route){
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = state.passwordInputText,
            onValueChange = {
                viewModel.handleEvent(SettingPasswordEvent.PasswordUpdate(it))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            label = {
                Text(
                    text = createPassword,
                    color = MaterialTheme.colorScheme.primary
                )
                    },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
        )
        OutlinedTextField(
            value = state.hintInputText,
            onValueChange = {
                viewModel.handleEvent(SettingPasswordEvent.HintUpdate(it))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            label = {
                Text(
                    text = createHint,
                    color = MaterialTheme.colorScheme.primary
                )
                    },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        state.errorText?.asString()?.let {
            Text(
                text = it,
                style = TextStyle(color = MaterialTheme.colorScheme.error)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                viewModel.handleEvent(SettingPasswordEvent.InsertPassword)
            }
        ) {
            Text(text = submit)
        }
    }
}