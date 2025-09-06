package br.dev.geanbrandao.howtodo.newpokedex.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.geanbrandao.howtodo.newpokedex.common.orFalse
import br.dev.geanbrandao.howtodo.newpokedex.data.preferences.PreferencesData
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonV2Repository
import br.dev.geanbrandao.howtodo.newpokedex.navigation.AppNavigator
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY_UI_STATE = "keyUiDetailsState"

@KoinViewModel
class DetailsViewModel(
    private val state: SavedStateHandle,
    private val appNavigator: AppNavigator,
    private val repository: PokemonV2Repository,
    private val preferencesData: PreferencesData,
) : ViewModel() {

    val uiState: StateFlow<DetailsUiState> = state.getStateFlow(KEY_UI_STATE, DetailsUiState())

    fun getPokemonDetails(id: Int) = viewModelScope.launch {
        preferencesData.setUpdateId(id)
        repository.getPokemonDetailsById(id)
            .onStart {
                state[KEY_UI_STATE] = uiState.value.copy(isLoading = true)
            }
            .catch {
                it.printStackTrace()
                state[KEY_UI_STATE] = uiState.value.copy(error = it, isLoading = false)
            }
            .collect { pokemon: PokemonV2Details ->
                state[KEY_UI_STATE] = uiState.value.copy(pokemon = pokemon, )
            }
    }

    fun navigateBack() = viewModelScope.launch {
        appNavigator.navigateBack()
    }

    fun onHeartClicked(id: Int) = viewModelScope.launch {
        repository.updateFavorite(id, !uiState.value.pokemon?.pokemon?.isFavorite.orFalse())
            .catch { it.printStackTrace() }
            .collect { state[KEY_UI_STATE] = uiState.value.copy(pokemon = uiState.value.pokemon?.copy(pokemon = it)) }
    }
}
