package br.dev.geanbrandao.howtodo.newpokedex.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonV2DetailsResponse(
    @SerialName("id") val id: Int,
    @SerialName("gender_rate") val genderRate: Int,
    @SerialName("evolution_chain") val evolutionChain: EvolutionChain,
) {

    @Serializable
    data class EvolutionChain(
        @SerialName("url") val url: String,
    )
}
