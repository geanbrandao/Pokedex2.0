package br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionResponse(
    @SerialName("chain") val chain: Chain,
) {
    @Serializable
    data class Chain(
        @SerialName("evolves_to") val evolvesTo: List<Chain>,
        @SerialName("species") val species: OutUrl,
    )
}
