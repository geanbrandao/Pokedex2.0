package br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import kotlinx.parcelize.Parcelize

@Parcelize
data class EvolutionChainUiState(
    val evolutions: List<PokemonV2> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) : Parcelable