package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonV2Repository
import br.dev.geanbrandao.howtodo.newpokedex.navigation.AppNavigator
import br.dev.geanbrandao.howtodo.newpokedex.navigation.Details
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.HomeUiState.Companion.PAGE_SIZE
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiHomeState"

@KoinViewModel
class HomeViewModel(
    private val state: SavedStateHandle,
    private val appNavigator: AppNavigator,
    private val repository: PokemonV2Repository,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = state.getStateFlow(
        key = KEY_UI_STATE,
        initialValue = HomeUiState()
    )

    fun loadPokemonList() = viewModelScope.launch {

        if (isAlreadyLoaded()) return@launch

        state[KEY_UI_STATE] = uiState.value.copy(isLoading = true)
        repository.getPokemonPage(currentPage = 1)
            .catch {
                it.printStackTrace()
            }
            .collect { pokemon: PokemonV2 ->
                state[KEY_UI_STATE] = uiState.value.copy(items = uiState.value.items + pokemon)
                state[KEY_UI_STATE] = uiState.value.copy(isLoading = isLoading())
            }
    }

    private fun isAlreadyLoaded(): Boolean =
        uiState.value.items.size == PAGE_SIZE * uiState.value.currentPage

    private fun isLoading(): Boolean {
        val currentSize = uiState.value.items.size
        val requiredSize = PAGE_SIZE * uiState.value.currentPage
        return currentSize < requiredSize
    }

    fun onTryAgain() {
        state[KEY_UI_STATE] = uiState.value.copy(error = null)
        loadPokemonList() // todo fazer os erros serem modulares, caso falhar o load de algum pokemon unitariamente
    }

    fun openDetails(id: Int) = viewModelScope.launch {
        appNavigator.navigateTo(Details(id))
    }
}