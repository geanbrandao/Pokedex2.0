package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import br.dev.geanbrandao.howtodo.newpokedex.common.clickableNoRippleEffect
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.HomeUiStatePreviewProvider
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.ErrorScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonCard
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonShimmerItem
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreenV2(
    viewModel: HomeViewModel = koinViewModel(),
) {

    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.error != null) {
        ErrorScreen {
            viewModel.onTryAgain()
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.loadPokemonList()
        }
    }

    PokemonListView(
        uiState = uiState.value,
        navigateToDetails = { id: Int ->
            viewModel.openDetails(id)
        },
    )

}

@Composable
private fun PokemonListView(
    uiState: HomeUiState,
    navigateToDetails: (Int) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(all = PaddingOne),
        state = listState,
    ) {
        items(
            items = uiState.items,
            key = { it.id },
        ) { item: PokemonV2 ->
            PokemonCard(
                item = item,
                modifier = Modifier
                    .padding(bottom = PaddingTwo)
                    .clickableNoRippleEffect {
                        navigateToDetails(item.id)
                    }
            )
        }
        if (uiState.isLoading) {
            item {
                PokemonShimmerItem()
                PokemonShimmerItem()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonListPreview(
    @PreviewParameter(HomeUiStatePreviewProvider::class) uiState: HomeUiState,
) {
    AppTheme {
        PokemonListView(
            uiState = uiState,
            navigateToDetails = {},
        )
    }
}
