package com.wip.tech.visitcroatia.data

data class Attraction(
    val description: String = "",
    val facts: List<String> = listOf(),
    val id: String = "",
    val image_urls: String = "",
    val location: Location = Location(),
    val months_to_visit: String = "",
    val title: String = ""
) {
    data class Location(
        val latitude: String = "",
        val longitude: String = ""
    )
}