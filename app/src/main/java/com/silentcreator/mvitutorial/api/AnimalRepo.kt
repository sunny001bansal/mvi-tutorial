package com.silentcreator.mvitutorial.api

import android.util.Log
import com.silentcreator.mvitutorial.model.Animal
import com.silentcreator.mvitutorial.model.AnimalDto

class AnimalRepo(
    private val api: AnimalApi
) {
    suspend fun getAnimals(): List<Animal> {
        val apiResponse = api.getAnimals()
        Log.e("MyResponseForApi", apiResponse.toString())

        return apiResponse.map { it.toDomain() }
    }

    private fun AnimalDto.toDomain() = Animal(
        name = name,
        location = location,
        imagePath = image,
    )
}