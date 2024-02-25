package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class PokemonDetailsModel(
    val pokemon: PokemonModel,
    val evolutionUrl: String,
    val generation: String,
    val genderRate: Int,
): Parcelable
