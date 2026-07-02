package com.silentcreator.mvitutorial.api

import com.silentcreator.mvitutorial.model.AnimalDto
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<AnimalDto>
}