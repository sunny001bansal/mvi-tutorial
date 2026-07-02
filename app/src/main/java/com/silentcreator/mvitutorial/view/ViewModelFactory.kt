package com.silentcreator.mvitutorial.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.silentcreator.mvitutorial.api.AnimalApi
import com.silentcreator.mvitutorial.api.AnimalRepo

class ViewModelFactory(
    private val api: AnimalApi
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(AnimalRepo(api)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
