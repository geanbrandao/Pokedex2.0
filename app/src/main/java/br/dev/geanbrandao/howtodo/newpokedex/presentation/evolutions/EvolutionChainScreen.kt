package br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.common.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.common.gradient45
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.PokemonPreviewProvider
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.PreviewHelper
import br.dev.geanbrandao.howtodo.newpokedex.common.shimmerEffect
import br.dev.geanbrandao.howtodo.newpokedex.common.toColor
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.ErrorScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonType
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.debugPlaceholder
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeSmallSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingThree
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun EvolutionChainScreen(
    modifier: Modifier = Modifier,
    viewModel: EvolutionChainViewModel = koinViewModel(),
    evolutions: List<Int>,
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getEvolutions(evolutions)
        }
    }

    val uiState = viewModel.uiState.collectAsState()

    EvolutionChainView(
        modifier = modifier,
        uiState = uiState.value,
        onTryAgain = { viewModel.getEvolutions(evolutions) }
    )
}

@Composable
private fun EvolutionChainView(
    modifier: Modifier = Modifier,
    uiState: EvolutionChainUiState,
    onTryAgain: () -> Unit = {},
) {

    if (uiState.error != null) {
        ErrorScreen(
            onTryAgain = onTryAgain
        )
    } else {
        Column(
            modifier = modifier,
        ) {
            Text(
                text = stringResource(R.string.pokemon_details_label_evolutions),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.size(size = PaddingTwo))
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shimmerEffect(RoundedCornerShape(size = 75.dp))
                        .border(
                            shape = RoundedCornerShape(size = 75.dp),
                            width = 1.dp,
                            color = Black.copy(alpha = 0.1f),
                        )
                        .height(100.dp),
                )
            } else {
                uiState.evolutions.forEach { pokemon: PokemonV2 ->
                    EvolutionItem(pokemon = pokemon)
                    Spacer(modifier = Modifier.size(size = PaddingTwo))
                }
            }
        }

    }

}

@Composable
private fun EvolutionItem(
    pokemon: PokemonV2,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = PaddingThree),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PokemonImageView(pokemon = pokemon)
            Spacer(modifier = Modifier.size(size = PaddingTwo))
            Column {
                Text(
                    text = pokemon.name.capitalize(),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = pokemon.numberFormatted,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.size(size = PaddingOne))
                Row {
                    PokemonType(
                        type = pokemon.typeOne,
                        iconSize = IconTypeSmallSize,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    pokemon.typeTwo?.let { type: PokemonTypeModel ->
                        Spacer(modifier = Modifier.size(size = PaddingHalf))
                        PokemonType(
                            type = type,
                            iconSize = IconTypeSmallSize,
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun PokemonImageView(pokemon: PokemonV2) {
    ConstraintLayout(
        modifier = Modifier
            .background(color = pokemon.typeOne.color.toColor(), CardDefaults.shape)
            .padding(all = PaddingOne)
            .size(width = 100.dp, 80.dp)
    ) {
        val (bgRef, imgRef) = createRefs()
        Icon(
            painter = painterResource(id = pokemon.typeOne.icon),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            brush = pokemon.typeOne.color.toColor().gradient45,
                            blendMode = BlendMode.SrcAtop
                        )
                    }
                }
                .constrainAs(bgRef) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        AsyncImage(
            model = pokemon.imgUrlNormal,
            placeholder = debugPlaceholder(R.drawable.il_error),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(50.dp)
                .constrainAs(imgRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

class EvolutionChainPreviewProvider : PreviewParameterProvider<EvolutionChainUiState> {
    val uiState = EvolutionChainUiState()
    override val values: Sequence<EvolutionChainUiState>
        get() = sequenceOf(
            uiState.copy(isLoading = true),
            uiState.copy(evolutions = listOf(PreviewHelper.bulbasaur, PreviewHelper.ivysaur, PreviewHelper.venusaur)),
            uiState.copy(error = Throwable())
        )

}

@Preview(showBackground = true)
@Composable
private fun PokemonEvolutionsPreview(
    @PreviewParameter(EvolutionChainPreviewProvider::class) uiState: EvolutionChainUiState,
) {
    AppTheme {
        EvolutionChainView(uiState = uiState)
    }
}

@Preview
@Composable
private fun PokemonImagePreview(
    @PreviewParameter(PokemonPreviewProvider::class) pokemon: PokemonV2
) {
    AppTheme {
        PokemonImageView(pokemon = pokemon)
    }
}
