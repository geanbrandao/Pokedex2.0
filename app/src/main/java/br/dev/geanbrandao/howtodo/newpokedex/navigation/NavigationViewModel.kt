package br.dev.geanbrandao.howtodo.newpokedex.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val KEY = "navigationStateKey"

@KoinViewModel
class NavigationViewModel(
    private val state: SavedStateHandle,
    private val appNavigator: AppNavigator,
) : ViewModel() {

    val backStack = state.getStateFlow<List<NavKey>>(KEY, initialBackStack)

    init {
        handleAppNavigator()
    }

    fun handleAppNavigator() = viewModelScope.launch{
        appNavigator.navigationChannel.receiveAsFlow().collect { intent ->
            when (intent) {
                is NavigationIntent.NavigateBack -> navigateBack(intent.n)
                is NavigationIntent.NavigateTo -> navigateTo(intent.screen)
            }
        }
    }

    fun navigateTo(screen: NavKey) {
        state[KEY] = backStack.value + screen
    }

    fun navigateBack(n: Int = 1) {
        state[KEY] = runCatching {
            backStack.value.dropLast(n)
        }.getOrDefault(emptyList())
    }
}