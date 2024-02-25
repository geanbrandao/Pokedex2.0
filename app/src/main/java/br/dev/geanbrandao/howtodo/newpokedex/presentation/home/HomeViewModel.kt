package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.domain.usecases.PokemonUseCases
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.HomeUiState.Companion.PAGE_SIZE
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiHomeState"

@KoinViewModel
class HomeViewModel(
    private val state: SavedStateHandle,
    private val useCases: PokemonUseCases,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = state.getStateFlow(KEY_UI_STATE, HomeUiState())

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            useCases.getPokemonListUseCase(currentPage = uiState.value.currentPage)
                .catch {
                    state[KEY_UI_STATE] = uiState.value.copy(error = Exception(it))
                }.collect { pokemon: PokemonModel ->
                    state[KEY_UI_STATE] = uiState.value.copy(pokemonList = uiState.value.pokemonList + pokemon)
                    state[KEY_UI_STATE] = uiState.value.copy(isLoading = isLoading())
                }
        }
    }

    private fun isLoading(): Boolean {
        val currentSize = uiState.value.pokemonList.size
        val requiredSize = PAGE_SIZE * uiState.value.currentPage
        return currentSize < requiredSize
    }

    fun onTryAgain() {
        state[KEY_UI_STATE] = uiState.value.copy(error = null)
        getPokemonList()
    }
}