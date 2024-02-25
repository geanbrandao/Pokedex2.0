package br.dev.geanbrandao.howtodo.newpokedex.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpecieResponse(
    @SerialName("gender_rate") val genderRate: Int,
    @SerialName("evolution_chain") val evolutionChain: EvolutionChain,
    @SerialName("generation") val generation: Generation,
) {
    @Serializable
    data class EvolutionChain(
        @SerialName("url") val url: String,
    )

    @Serializable
    data class Generation(
        @SerialName("name") val name: String,
    )
}
