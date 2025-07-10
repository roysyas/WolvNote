package com.roys.wolvnote.presentation.note.mainpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.data.database.NoteTable
import com.roys.wolvnote.domain.usecase.DeleteNoteUseCase
import com.roys.wolvnote.domain.usecase.GetNotesUseCase
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
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getNotes()
    }

    fun handleEvent(viewEvent: HomeEvent){
        when(viewEvent){
            is HomeEvent.OnToggle -> onToggle(viewEvent.toggle)
            is HomeEvent.OnRefresh -> getNotes()
            is HomeEvent.OnDelete -> deleteNote(viewEvent.item)
        }
    }

    private fun onToggle(toggle: Boolean){
        _state.update {
            it.copy(
                isToggle = toggle
            )
        }
    }

    private fun deleteNote(item: NoteTable){
        deleteNoteUseCase.invoke(item).onEach { result->
            when(result){
                is Resource.Error -> {
                    viewModelScope.launch {
                        SnackBarController.sendEvent(
                            event = SnackBarError(
                                message = result.message ?: "An unexpected error occurred"
                            )
                        )
                    }
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    getNotes()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNotes(){
        getNotesUseCase.invoke().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    if(result.data.isNullOrEmpty()){
                        _state.value = HomeState(isEmpty = true)
                    }else{
                        _state.value = HomeState(noteList = result.data)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        HomeState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}