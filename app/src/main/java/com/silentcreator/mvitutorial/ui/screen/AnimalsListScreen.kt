package com.silentcreator.mvitutorial.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.silentcreator.mvitutorial.api.AnimalService
import com.silentcreator.mvitutorial.model.Animal

@Composable
fun AnimalsListScreen(animals: List<Animal>) {

    LazyColumn {
        items(animals.size) {
            AnimalItem(animals[it])
            HorizontalDivider(thickness = 8.dp)
        }
    }
}

@Composable
fun AnimalItem(animal: Animal) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(100.dp)
    ) {
        val imageUrl = AnimalService.BASE_URL + animal.imagePath
        val painter = rememberAsyncImagePainter(model = imageUrl)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Text(
                text = animal.name,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Located in: ${animal.location}")
        }
    }
}