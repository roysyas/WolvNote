package com.roys.wolvnote.presentation.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.roys.wolvnote.common.checkFirstInstall
import com.roys.wolvnote.presentation.ui.theme.WolvNoteTheme
import com.roys.wolvnote.presentation.ui.util.AppNavHost
import com.roys.wolvnote.presentation.ui.util.ObserveAsEvents
import com.roys.wolvnote.presentation.ui.util.SnackBarController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkFirstInstall(this)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        setContent {
            WolvNoteTheme {
                val scope = rememberCoroutineScope()
                val snackBarHostState = remember { SnackbarHostState() }
                ObserveAsEvents(
                    flow = SnackBarController.events,
                    snackBarHostState
                ) { event ->
                    scope.launch {
                        snackBarHostState.currentSnackbarData?.dismiss()
                        snackBarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Long
                        )
                    }
                }

                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackBarHostState
                        )
                    }
                ) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavHost(navController, innerPadding)
                }
            }
        }
    }
}

