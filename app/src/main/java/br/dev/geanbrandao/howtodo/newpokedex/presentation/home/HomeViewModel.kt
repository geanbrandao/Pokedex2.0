package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.data.preferences.PreferencesData
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonV2Repository
import br.dev.geanbrandao.howtodo.newpokedex.navigation.AppNavigator
import br.dev.geanbrandao.howtodo.newpokedex.navigation.Details
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.HomeUiState.Companion.PAGE_SIZE
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiHomeState"

@KoinViewModel
class HomeViewModel(
    private val state: SavedStateHandle,
    private val appNavigator: AppNavigator,
    private val repository: PokemonV2Repository,
    private val preferencesData: PreferencesData,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = state.getStateFlow(
        key = KEY_UI_STATE,
        initialValue = HomeUiState()
    )

    fun loadPokemonList() = viewModelScope.launch {
        if (isAlreadyLoaded()) {
            preferencesData.updateId.collect { updatedId: Int ->
                updatedId.takeIf { it != -1 }?.let(::refreshFavoriteById)
            }
            return@launch
        }

        repository.getPokemonPage(currentPage = uiState.value.currentPage)
            .onStart {
                state[KEY_UI_STATE] = uiState.value.copy(isLoading = true)
            }
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

    fun onLoadMore() {
        if (isLoading()) return
        state[KEY_UI_STATE] = uiState.value.copy(currentPage = uiState.value.currentPage + 1)
        loadPokemonList()
    }

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

    fun onHeartClicked(id: Int, isFavorite: Boolean) = viewModelScope.launch {
        repository.updateFavorite(id,  isFavorite.not())
            .catch { it.printStackTrace() }
            .collect { state[KEY_UI_STATE] = uiState.value.copy(items = updateList(it) ) }
    }

    fun refreshFavoriteById(id: Int) = viewModelScope.launch {
        val isFavorite = repository.getPokemonByIdIsFavorite(id)
        state[KEY_UI_STATE] =  uiState.value.copy(items = updateIsFavoriteById(id, isFavorite))
    }

    private fun updateIsFavoriteById(id: Int, isFavorite: Boolean): List<PokemonV2> {
        val currentList = uiState.value.items
        val index = currentList.indexOfFirst { it.id == id }

        return if (index != -1) {
            val updatedItem = currentList[index].copy(isFavorite = isFavorite)
            updateList(updatedItem)
        } else {
            currentList
        }
    }

    private fun updateList(updatedItem: PokemonV2) : List<PokemonV2> {
        val currentList = uiState.value.items
        val index = currentList.indexOfFirst { it.id == updatedItem.id }
        return if (index != -1) {
            currentList.toMutableList().apply {
                set(index, updatedItem)
            }
        } else {
            currentList + updatedItem
        }
    }
}