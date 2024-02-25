package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import kotlinx.parcelize.Parcelize


@Parcelize
data class HomeUiState(
    val pokemonList: List<PokemonModel> = emptyList(),
    val currentPage: Int = 1,
    val isLoading: Boolean = false,
    val error: Exception? = null,
): Parcelable {

    companion object {
        const val PAGE_SIZE = 20
    }
}
