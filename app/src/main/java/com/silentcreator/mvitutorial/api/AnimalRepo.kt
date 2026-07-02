package com.silentcreator.mvitutorial.api

import com.silentcreator.mvitutorial.model.Animal
import com.silentcreator.mvitutorial.model.AnimalDto

class AnimalRepo(
    private val api: AnimalApi
) {
    suspend fun getAnimals(): List<Animal> {
        val apiResponse = api.getAnimals()
        return apiResponse.map { it.toDomain() }
    }

    private fun AnimalDto.toDomain() = Animal(
        name = name,
        location = location,
        imagePath = image,
    )
}