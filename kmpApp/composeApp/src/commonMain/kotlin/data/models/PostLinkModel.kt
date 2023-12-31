package data.models

import kotlinx.serialization.Serializable

@Serializable
data class PostLinkModel(
    val link: String,
    val token: String
)
