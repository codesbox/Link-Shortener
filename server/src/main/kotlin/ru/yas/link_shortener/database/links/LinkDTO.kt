package ru.yas.link_shortener.database.links

import kotlinx.serialization.Serializable

@Serializable
class LinkDTO(
    val link: String,
    val token: String
)
