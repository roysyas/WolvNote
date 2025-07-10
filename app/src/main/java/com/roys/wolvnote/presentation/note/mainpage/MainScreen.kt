package com.roys.wolvnote.presentation.note.mainpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.roys.wolvnote.R
import com.roys.wolvnote.common.Constants
import com.roys.wolvnote.presentation.note.mainpage.component.CustomFloatingButton
import com.roys.wolvnote.presentation.note.mainpage.component.NoteItem
import com.roys.wolvnote.presentation.ui.util.Screen

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val emptyNotes = stringResource(R.string.empty_notes)

    LaunchedEffect(key1 = Unit) {
        navController.currentBackStackEntry?.savedStateHandle?.getStateFlow(Constants.REFRESH, false)?.collect { result->
            if(result){
                viewModel.handleEvent(HomeEvent.OnRefresh(true))
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        if(state.isEmpty){
            Text(
                text = emptyNotes,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if(state.noteList.isNotEmpty()){
            NoteItem(
                listNoteTable = state.noteList,
                navController = navController,
                onDelete = {data->
                    viewModel.handleEvent(HomeEvent.OnDelete(data))
                }
            )
        }
        Box(
            modifier = Modifier
                .padding(0.dp,0.dp,8.dp,8.dp)
                .align(Alignment.BottomEnd)
        ){
            CustomFloatingButton(
                state,
                onClick = { result ->
                    when(result){
                        Constants.CATEGORY_CHECKLIST ->{
                            navController.navigate(Screen.CreateCheckedListScreen.route)
                        }
                        Constants.CATEGORY_NOTE ->{
                            navController.navigate(Screen.CreateNoteScreen.route)
                        }
                        Constants.CATEGORY_SALARY ->{
                            navController.navigate(Screen.CreateSalaryScreen.route)
                        }

                        Constants.CATEGORY_DRAW ->{
                            navController.navigate(Screen.CreateDrawScreen.route)
                        }
                    }
                },
                onToggle = {
                    viewModel.handleEvent(HomeEvent.OnToggle(it))
                }
            )
        }
    }
}