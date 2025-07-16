package br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonV2Repository
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiEvolutionChainState"

@KoinViewModel
class EvolutionChainViewModel(
    private val state: SavedStateHandle,
    private val repository: PokemonV2Repository,
) : ViewModel() {

    val uiState: StateFlow<EvolutionChainUiState> = state.getStateFlow(
        key = KEY_UI_STATE,
        initialValue = EvolutionChainUiState()
    )

    fun getEvolutions(evolutions: List<Int>) = viewModelScope.launch {
        if (evolutions.size == uiState.value.evolutions.size) return@launch
        repository.getEvolutions(evolutions)
            .onStart {
                state[KEY_UI_STATE] = uiState.value.copy(isLoading = true)
            }
            .catch {
                it.printStackTrace()
            }.collect { pokemon: PokemonV2 ->
                state[KEY_UI_STATE] = uiState.value.copy(
                    evolutions = uiState.value.evolutions + pokemon,
                    isLoading = uiState.value.evolutions.size == evolutions.size)
            }
    }
}