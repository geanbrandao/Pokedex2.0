package br.dev.geanbrandao.howtodo.newpokedex.presentation.details

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details
import kotlinx.parcelize.Parcelize

//@Parcelize
//data class DetailsUiState(
//    val pokemonDetails: PokemonDetailsModel? = null,
//    val isFavorite: Boolean = false,
//    val screenUiState: ScreenUiState = ScreenUiState(),
//) : Parcelable {
//
//    companion object {
//        fun DetailsUiState.update(
//            pokemonDetails: PokemonDetailsModel? = this.pokemonDetails,
//            isFavorite: Boolean = this.isFavorite,
//            isLoading: Boolean = this.screenUiState.isLoading,
//            error: Exception? = this.screenUiState.error,
//        ): DetailsUiState {
//            return this.copy(
//                pokemonDetails = pokemonDetails,
//                isFavorite = isFavorite,
//                screenUiState = this.screenUiState.copy(
//                    isLoading = isLoading,
//                    error = error,
//                )
//            )
//        }
//    }
//}

@Parcelize
data class DetailsUiState(
    val pokemon: PokemonV2Details? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) : Parcelable
