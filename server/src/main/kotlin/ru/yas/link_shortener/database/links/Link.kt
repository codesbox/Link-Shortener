package ru.yas.link_shortener.database.links

import org.bson.codecs.pojo.annotations.BsonId

data class Link(
    @BsonId
    val id: String,
    val link: String
)
