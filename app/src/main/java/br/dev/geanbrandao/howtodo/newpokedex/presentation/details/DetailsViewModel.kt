package br.dev.geanbrandao.howtodo.newpokedex.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.domain.usecases.PokemonUseCases
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.DetailsUiState.Companion.update
import br.dev.geanbrandao.howtodo.newpokedex.presentation.navArgs
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiDetailsState"

@KoinViewModel
class DetailsViewModel(
    private val state: SavedStateHandle,
    private val useCases: PokemonUseCases,
) : ViewModel() {

    private val navArgs: PokemonDetailsScreenNavArgs = state.navArgs()

    val uiState: StateFlow<DetailsUiState> = state.getStateFlow(KEY_UI_STATE, DetailsUiState())

    init {
        getPokemonDetails()
    }

    fun getPokemonDetails() {
        viewModelScope.launch {
            state[KEY_UI_STATE] = uiState.value.update(isLoading = true)
            try {
                val result = useCases.getPokemonDetailsUseCase(navArgs.pokemon)
                state[KEY_UI_STATE] = uiState.value.update(pokemonDetails = result)
            } catch (e: Exception) {
                state[KEY_UI_STATE] = uiState.value.update(error = e)
            } finally {
                state[KEY_UI_STATE] = uiState.value.update(isLoading = false)
            }
        }
    }
}