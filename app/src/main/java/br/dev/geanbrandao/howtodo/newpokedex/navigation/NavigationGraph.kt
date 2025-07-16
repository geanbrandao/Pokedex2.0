package br.dev.geanbrandao.howtodo.newpokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.PokemonDetailsScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.PokemonListScreenV2
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel = koinViewModel(),
) {

    val backStack = navigationViewModel.backStack.collectAsState()

    NavDisplay(
        modifier = modifier,
        backStack = backStack.value,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = {
            navigationViewModel.navigateBack(it)
        },
        sceneStrategy = TwoPaneSceneStrategy<Any>(),
        entryProvider = entryProvider {
            entry<Home>(
                metadata = TwoPaneScene.twoPane(),
                content = {
//                    PokemonListScreen()
                    PokemonListScreenV2()
                }
            )
            entry<Details>(
                metadata = TwoPaneScene.twoPane(),
                content = {
                    PokemonDetailsScreen(pokemonId = it.id)
                }
            )
        }
    )

}