package com.silentcreator.mvitutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.silentcreator.mvitutorial.api.AnimalService
import com.silentcreator.mvitutorial.ui.screen.MainScreen
import com.silentcreator.mvitutorial.ui.theme.MVITutorialTheme
import com.silentcreator.mvitutorial.view.MainIntent
import com.silentcreator.mvitutorial.view.MainViewModel
import com.silentcreator.mvitutorial.view.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(AnimalService.api)
        )[MainViewModel::class.java]

        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                viewModel.userInteraction.send(MainIntent.FetchAnimals)
            }
        }

        enableEdgeToEdge()
        setContent {
            MVITutorialTheme {
                Surface {
                    MainScreen(viewModel = viewModel, onButtonClick = onButtonClick)
                }
            }
        }
    }
}