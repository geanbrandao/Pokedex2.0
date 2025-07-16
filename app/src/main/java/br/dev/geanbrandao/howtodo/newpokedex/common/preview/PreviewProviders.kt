package br.dev.geanbrandao.howtodo.newpokedex.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.HomeUiState
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details

class PokemonDetailsPreviewProvider : PreviewParameterProvider<PokemonV2Details> {
    override val values: Sequence<PokemonV2Details>
        get() = sequenceOf(
            PreviewHelper.bulbasaurDetails,
            PreviewHelper.ivysaurDetails,
            PreviewHelper.venusaurDetails,
        )
}

class PokemonPreviewProvider: PreviewParameterProvider<PokemonV2> {
    override val values: Sequence<PokemonV2>
        get() = sequenceOf(
            PreviewHelper.bulbasaur,
            PreviewHelper.ivysaur,
            PreviewHelper.venusaur,
        )
}

class HomeUiStatePreviewProvider: PreviewParameterProvider<HomeUiState> {
    val uiState = HomeUiState()
    override val values: Sequence<HomeUiState>
        get() = sequenceOf(
            uiState.copy(
                items = listOf(
                    PreviewHelper.bulbasaur,
                    PreviewHelper.ivysaur,
                    PreviewHelper.venusaur,
                ),
            ),
            uiState.copy(isLoading = true),
            uiState.copy(error = Throwable()),
            uiState.copy(
                items = listOf(
                    PreviewHelper.bulbasaur,
                    PreviewHelper.ivysaur,
                    PreviewHelper.venusaur,
                    PreviewHelper.charmander,
                    PreviewHelper.squirtle,
                    PreviewHelper.pikachu,
                    PreviewHelper.clefairy,
                    PreviewHelper.onix,
                    PreviewHelper.koffing,
                    PreviewHelper.mew,
                    PreviewHelper.ditto,
                    PreviewHelper.suicune,
                    PreviewHelper.rayquaza,
                    PreviewHelper.toucannon,
                ),
            ),

        )
}