package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonV2Details(
    val pokemon: PokemonV2,
    val genderRate: Int,
    val evolutions: List<Int>,
) : Parcelable
