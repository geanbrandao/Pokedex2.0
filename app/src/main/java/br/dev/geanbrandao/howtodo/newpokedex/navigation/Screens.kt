package br.dev.geanbrandao.howtodo.newpokedex.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data object Home : NavKey, Parcelable

@Serializable
@Parcelize
data class Details(val item: PokemonModel): NavKey, Parcelable

val initialBackStack = listOf(Home)