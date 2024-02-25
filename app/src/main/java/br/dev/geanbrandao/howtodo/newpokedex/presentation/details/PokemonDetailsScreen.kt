package br.dev.geanbrandao.howtodo.newpokedex.presentation.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.ErrorScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components.HeaderView
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components.PokemonDetailsInfo
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components.TopHeaderView
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.Bulbasaur
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonDetailsModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination(
    navArgsDelegate = PokemonDetailsScreenNavArgs::class
)
@Composable
fun PokemonDetailsScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailsViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    if (uiState.value.screenUiState.error != null) {
        ErrorScreen {
            viewModel.getPokemonDetails()
        }
    }

    uiState.value.pokemonDetails?.let {
        PokemonDetailsView(
            item = it,
            onBackPressed = {
                navigator.popBackStack()
            }
        )
    }
}

@Composable
private fun PokemonDetailsView(
    item: PokemonDetailsModel = Bulbasaur,
    onBackPressed: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val isScrollingUp = scrollState.rememberIsScrollingDown()
    val topHeaderColor = animateColorAsState(
        targetValue = if (isScrollingUp) item.pokemon.typeOne.color else Color.Unspecified,
        label = "Top header color",
        animationSpec = tween(700),
    )
    Box {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState),
        ) {
            HeaderView(pokemon = item.pokemon)
            Spacer(modifier = Modifier.size(size = PaddingTwo))
            PokemonDetailsInfo(pokemon = item)
        }
        TopHeaderView(
            modifier = Modifier.background(color = topHeaderColor.value),
            onBackPressed = onBackPressed,
        )
    }
}

@Composable
private fun ScrollState.rememberIsScrollingDown(): Boolean {
    val density = LocalDensity.current
    return remember(this) {
        derivedStateOf {
            println("ScrollValue ${this.value}")
            val scrollDp = with(density) { this@rememberIsScrollingDown.value.toDp() }
            scrollDp >= 96.dp
        }
    }.value
}

//@Composable
//private fun LazyListState.isScrollingUp(): Boolean {
//    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
//    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
//    return remember(this) {
//        derivedStateOf {
//            if (previousIndex != firstVisibleItemIndex) {
//                previousIndex > firstVisibleItemIndex
//            } else {
//                previousScrollOffset >= firstVisibleItemScrollOffset
//            }.also {
//                previousIndex = firstVisibleItemIndex
//                previousScrollOffset = firstVisibleItemScrollOffset
//            }
//        }
//    }.value
//}


@Preview(showBackground = true)
@Composable
private fun PokemonDetailsPreview() {
    PokemonDetailsView()
}