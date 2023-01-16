package com.wip.tech.visitcroatia.data

data class Attraction(
    val description: String,
    val facts: List<String>,
    val id: String,
    val image_urls: List<String>,
    val location: Location,
    val months_to_visit: String,
    val title: String
)