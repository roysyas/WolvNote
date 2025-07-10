package com.roys.wolvnote.presentation.ui.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.roys.wolvnote.common.Constants
import com.roys.wolvnote.presentation.auth.login.AuthScreen
import com.roys.wolvnote.presentation.auth.settingpassword.SettingPasswordScreen
import com.roys.wolvnote.presentation.note.checklist.CreateCheckedListScreen
import com.roys.wolvnote.presentation.note.draw.CreateDrawScreen
import com.roys.wolvnote.presentation.note.notetaker.CreateNoteScreen
import com.roys.wolvnote.presentation.note.salarycalculation.CreateSalaryScreen
import com.roys.wolvnote.presentation.note.mainpage.MainScreen

@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.AuthScreen.route
    ) {
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(navController)
        }
        composable(route = Screen.SettingPasswordScreen.route) {
            SettingPasswordScreen(navController)
        }
        composable(route = Screen.MainScreen.route){
            MainScreen(navController)
        }
        composable(
            route = Screen.CreateCheckedListScreen.route + "?{${Constants.NOTE_ID}}",
            arguments = listOf(
                navArgument(Constants.NOTE_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ){
            CreateCheckedListScreen(navController)
        }
        composable(
            route = Screen.CreateNoteScreen.route + "?{${Constants.NOTE_ID}}",
            arguments = listOf(
                navArgument(Constants.NOTE_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ){
            CreateNoteScreen(navController)
        }
        composable(
            route = Screen.CreateSalaryScreen.route + "?{${Constants.NOTE_ID}}",
            arguments = listOf(
                navArgument(Constants.NOTE_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ){
            CreateSalaryScreen((navController))
        }
        composable(
            route = Screen.CreateDrawScreen.route + "?{${Constants.NOTE_ID}}",
            arguments = listOf(
                navArgument(Constants.NOTE_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ){
            CreateDrawScreen(navController)
        }
    }
}