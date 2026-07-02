package com.silentcreator.mvitutorial.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.silentcreator.mvitutorial.api.AnimalRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: AnimalRepo
): ViewModel() {

    val userInteraction = Channel<MainIntent>(Channel.UNLIMITED)

    val state = mutableStateOf<MainState>(MainState.Idle)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userInteraction.consumeAsFlow()
                .collect { intent ->
                    when (intent) {
                        MainIntent.FetchAnimals -> fetchAnimals()
                    }
                }
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            state.value = MainState.Loading

            state.value = try {
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage?: "Something went wrong")
            }
        }
    }
}