package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import androidx.compose.runtime.Stable
import br.dev.geanbrandao.howtodo.newpokedex.common.Utils
import kotlinx.parcelize.Parcelize


@Stable
@Parcelize
data class PokemonModel(
    val id: Int,
    val numberName: String,
    val name: String,
    val height: Float,
    val weight: Float,
    val abilities: List<String>,
    val stats: List<Stat>,
    val typeOne: PokemonTypeModel,
    val typeTwo: PokemonTypeModel? = null,
) : Parcelable {
    val imgUrlNormal: String get() = Utils.getImgOfficialNormal(id)
    val imgUrlShiny: String get() = Utils.getImgOfficialShiny(id)

    @Parcelize
    data class Stat(
        val name: String,
        val value: Int,
    ) : Parcelable
}
