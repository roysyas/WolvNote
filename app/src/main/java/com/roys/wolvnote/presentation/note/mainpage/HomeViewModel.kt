package com.roys.wolvnote.presentation.note.mainpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roys.wolvnote.common.DateTimeHelper
import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.data.database.NoteTable
import com.roys.wolvnote.domain.usecase.DeleteNoteUseCase
import com.roys.wolvnote.domain.usecase.GetNoteUseCase
import com.roys.wolvnote.domain.usecase.GetNotesUseCase
import com.roys.wolvnote.domain.usecase.LocationUseCase
import com.roys.wolvnote.domain.usecase.WeatherUseCase
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
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val weatherUseCase: WeatherUseCase,
    private val locationUseCase: LocationUseCase,
    private val getNoteUseCase: GetNoteUseCase
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
            is HomeEvent.OnDelete -> prepareDeleteNote(viewEvent.item)
            is HomeEvent.RequestPermission -> fetchLocation(viewEvent.granted)
        }
    }

    private fun onToggle(toggle: Boolean){
        _state.update {
            it.copy(
                isToggle = toggle
            )
        }
    }

    private fun prepareDeleteNote(item: Int?){
        item?.let { getNote(it) }
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
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
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
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Success -> {
                    if(result.data.isNullOrEmpty()){
                        _state.update {
                            it.copy(
                                isEmpty = true
                            )
                        }
                    }else{
                        _state.update {
                            it.copy(
                                noteList = result.data
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNote(id: Int){
        getNoteUseCase.invoke(id).onEach { result->
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
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Success -> {
                    result.data?.let {
                        deleteNote(result.data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchLocation(granted: Boolean){
        _state.update {
            it.copy(
                isGranted = granted
            )
        }
        if(granted){
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        location = locationUseCase()
                    )
                }
                getWeather()
            }
        }
    }

    private fun getWeather(){
        val lat = _state.value.location?.latitude
        val lng = _state.value.location?.longitude
        val timezone = DateTimeHelper.getTimeZone()

        lat?.let {
            lng?.let {
                weatherUseCase.invoke(lat, lng, timezone).onEach { result->
                    when(result){
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    weatherError = result.message ?: "An unexpected error occurred"
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    weatherIsLoading = true
                                )
                            }
                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    currentWeather = result.data
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}