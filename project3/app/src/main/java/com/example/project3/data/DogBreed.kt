package com.example.project3.data

data class DogBreed(
    val id: Int,
    val name: String,
    val temperament: String? = "Unknown",
    val origin: String? = "Unknown",
    val imageUrl: String? = null
)