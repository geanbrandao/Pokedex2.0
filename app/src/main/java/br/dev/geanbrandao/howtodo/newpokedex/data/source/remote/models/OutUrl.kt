package br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutUrl(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)