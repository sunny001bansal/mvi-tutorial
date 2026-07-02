package com.silentcreator.mvitutorial.ui.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.silentcreator.mvitutorial.view.MainState
import com.silentcreator.mvitutorial.view.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onButtonClick: () -> Unit
) {
    val state = viewModel.state.value

    when (state) {
        is MainState.Idle -> IdleScreen(onButtonClick)
        is MainState.Loading -> LoadingScreen()
        is MainState.Animals -> AnimalsListScreen(animals = state.animals)
        is MainState.Error -> {
            IdleScreen(onButtonClick)
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
        }
    }
}