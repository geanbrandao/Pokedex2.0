package br.dev.geanbrandao.howtodo.newpokedex.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import org.koin.core.annotation.Single

@Single
class AppNavigator {
    val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    suspend fun navigateTo(screen: NavKey) {
        navigationChannel.send(
            NavigationIntent.NavigateTo(screen)
        )
    }

    suspend fun navigateBack(n: Int = 1) {
        navigationChannel.send(
            NavigationIntent.NavigateBack(n)
        )
    }
}

sealed class NavigationIntent {
    data class NavigateTo(val screen: NavKey) : NavigationIntent()
    data class NavigateBack(val n: Int = 1) : NavigationIntent()
}