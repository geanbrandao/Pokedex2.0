package br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.domain.usecases.PokemonUseCases
import br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions.EvolutionChainUiState.Companion.update
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiEvolutionChainState"

@KoinViewModel
class EvolutionChainViewModel(
    private val state: SavedStateHandle,
    private val useCases: PokemonUseCases,
) : ViewModel() {

    val uiState: StateFlow<EvolutionChainUiState> = state.getStateFlow(
        key = KEY_UI_STATE,
        initialValue = EvolutionChainUiState()
    )

    fun getEvolutionChain(chainUrl: String) {
        viewModelScope.launch {
            state[KEY_UI_STATE] = uiState.value.update(isLoading = true)
            try {
                val result = useCases.getEvolutionChainUseCase(url = chainUrl)
                state[KEY_UI_STATE] = uiState.value.update(evolutions = result)
            } catch (e: Exception) {
                state[KEY_UI_STATE] = uiState.value.update(error = Exception(e))
            } finally {
                state[KEY_UI_STATE] = uiState.value.update(isLoading = false)
            }
        }
    }
}