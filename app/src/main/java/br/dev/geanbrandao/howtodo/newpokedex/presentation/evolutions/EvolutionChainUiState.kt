package br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.ScreenUiState
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonEvolutionModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class EvolutionChainUiState(
    val evolutions: List<PokemonEvolutionModel> = listOf(),
    val screenUiState: ScreenUiState = ScreenUiState()
) : Parcelable {

    companion object {
        fun EvolutionChainUiState.update(
            evolutions: List<PokemonEvolutionModel> = this.evolutions,
            isLoading: Boolean = this.screenUiState.isLoading,
            error: Exception? = this.screenUiState.error,
        ) : EvolutionChainUiState {
            return this.copy(
                evolutions = evolutions,
                screenUiState = this.screenUiState.copy(
                    isLoading = isLoading,
                    error = error,
                )
            )
        }
    }
}