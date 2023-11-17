package dev.mathewsmobile.runes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Runes(
    val runes: List<Rune>
)

@JsonClass(generateAdapter = true)
data class Rune(
    val id: Int,
    val rune: Char,
    val name: String,
    val description: String,
    val keywords: List<String>,
    val link: String
)