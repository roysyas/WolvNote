package com.roys.wolvnote.presentation.note.mainpage.component

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LocationPermission(
    isGranted: Boolean,
    onGranted: (Boolean) -> Unit
){
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            onGranted(granted)
        }
    )

    LaunchedEffect(isGranted) {
        if(!isGranted){
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}