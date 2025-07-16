package br.dev.geanbrandao.howtodo.newpokedex.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameUrl(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String, // not used
)