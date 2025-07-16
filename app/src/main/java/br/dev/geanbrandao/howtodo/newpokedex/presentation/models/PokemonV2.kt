package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.common.Utils
import br.dev.geanbrandao.howtodo.newpokedex.common.toHeightName
import br.dev.geanbrandao.howtodo.newpokedex.common.toNumberName
import br.dev.geanbrandao.howtodo.newpokedex.common.toPokemonGenerationName
import br.dev.geanbrandao.howtodo.newpokedex.common.toWeightName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonV2(
    val id : Int,
    val name: String,
    val typeOne: PokemonTypeModel,
    val typeTwo: PokemonTypeModel?,
    val height: Float,
    val weight: Float,
    val abilities: List<String>,
    val stats: List<Stat>,
    val isFavorite: Boolean,
): Parcelable {
    val numberFormatted: String
        get() = id.toNumberName()

    val heightFormatted: String
        get() = height.toHeightName()

    val weightFormatted: String
        get() = weight.toWeightName()

    val generationFormatted: String
        get() = id.toPokemonGenerationName()
    val imgUrlNormal: String
        get() = Utils.getImgOfficialNormal(id)

    val imgUrlShiny: String
        get() = Utils.getImgOfficialShiny(id)

    @Parcelize
    data class Stat(
        val name: String,
        val value: Int,
    ) : Parcelable
}
