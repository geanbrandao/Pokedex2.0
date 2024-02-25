package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.common.Utils
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonEvolutionModel(
    val id: Int,
    val numberName: String,
    val name: String,
    val typeOne: PokemonTypeModel,
    val typeTwo: PokemonTypeModel? = null,
) : Parcelable {
    val imgUrlNormal: String get() = Utils.getImgOfficialNormal(id)
}
