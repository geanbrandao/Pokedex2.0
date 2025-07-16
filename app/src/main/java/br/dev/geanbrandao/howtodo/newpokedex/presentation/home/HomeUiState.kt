package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import android.os.Parcelable
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeUiState(
    val items: List<PokemonV2> = listOf(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val currentPage: Int = 1,
) : Parcelable {
    companion object {
        const val PAGE_SIZE = 10
    }
}
