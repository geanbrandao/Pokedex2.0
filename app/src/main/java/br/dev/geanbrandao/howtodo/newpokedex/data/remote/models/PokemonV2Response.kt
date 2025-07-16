package br.dev.geanbrandao.howtodo.newpokedex.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonV2Response(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("types") val types: List<Type>,
    @SerialName("abilities") val abilities: List<Ability>,
    @SerialName("stats") val stats: List<Stat>,
) {

    @Serializable
    data class Ability(
        @SerialName("slot") val slot: Int,
        @SerialName("ability") val ability: NameUrl,
    )

    @Serializable
    data class Stat(
        @SerialName("base_stat") val baseStat: Int,
        @SerialName("stat") val stat: NameUrl,
    )

    @Serializable
    data class Type(
        @SerialName("slot") val slot: Int,
        @SerialName("type") val type: NameUrl,
    )
}
