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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.common.gradient45
import br.dev.geanbrandao.howtodo.newpokedex.common.shimmerEffect
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.TextLabel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonTypeSmallView
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.debugPlaceholder
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonEvolutionModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingThree
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextBodyLarge
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelLarge
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelSmall
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun EvolutionChainScreen(
    chainUrl: String,
    modifier: Modifier = Modifier,
    viewModel: EvolutionChainViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getEvolutionChain(chainUrl = chainUrl)
    }

    val uiState = viewModel.uiState.collectAsState()
    val evolutions = uiState.value.evolutions
    val isLoading = uiState.value.screenUiState.isLoading

    EvolutionChainView(
        modifier = modifier,
        evolutions = evolutions,
        isLoading = isLoading,
    )
}

@Composable
private fun EvolutionChainView(
    modifier: Modifier = Modifier,
    evolutions: List<PokemonEvolutionModel>,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier,
    ) {
        PokemonName(
            text = stringResource(R.string.pokemon_details_label_evolutions),
            fontSize = TextLabelLarge,
        )
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        if (isLoading) {
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
            evolutions.forEach { pokemonEvolution: PokemonEvolutionModel ->
                EvolutionItem(pokemon = pokemonEvolution)
                Spacer(modifier = Modifier.size(size = PaddingTwo))
            }
        }
    }
}

@Composable
private fun EvolutionItem(
    pokemon: PokemonEvolutionModel = Bulbasaur,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                shape = RoundedCornerShape(size = 75.dp),
                width = 1.dp,
                color = Black.copy(alpha = 0.1f),
            )
            .padding(end = PaddingThree),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PokemonImageView(pokemon = pokemon)
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        Column {
            PokemonName(text = pokemon.name, fontSize = TextBodyLarge)
            TextLabel(text = pokemon.numberName, fontSize = TextLabelSmall)
            Spacer(modifier = Modifier.size(size = PaddingOne))
            Row {
                PokemonTypeSmallView(type = pokemon.typeOne)
                pokemon.typeTwo?.let { type: PokemonTypeModel ->
                    Spacer(modifier = Modifier.size(size = PaddingHalf))
                    PokemonTypeSmallView(type = type)
                }
            }
        }
    }
}

@Composable
private fun PokemonImageView(
    pokemon: PokemonEvolutionModel = Bulbasaur,
) {
    ConstraintLayout(
        modifier = Modifier
            .background(color = pokemon.typeOne.color, RoundedCornerShape(75.dp))
            .padding(all = PaddingOne)
            .size(width = 100.dp, 75.dp)
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
                            brush = pokemon.typeOne.color.gradient45,
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
            placeholder = debugPlaceholder(R.drawable.bulbasaur_1),
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

@Preview(showBackground = true)
@Composable
private fun PokemonEvolutionsPreview() {
    Column {
        EvolutionChainView(
            modifier = Modifier.fillMaxWidth(),
            evolutions = listOf(Bulbasaur, Ivysaur, Venusaur),
            isLoading = true,
        )
        EvolutionChainView(
            modifier = Modifier.fillMaxWidth(),
            evolutions = listOf(Bulbasaur, Ivysaur, Venusaur),
            isLoading = false,
        )
    }
}

@Preview
@Composable
private fun PokemonImagePreview() {
    PokemonImageView()
}

val Bulbasaur = PokemonEvolutionModel(
    numberName = "Nº001",
    name = "bulbasaur",
    id = 1,
    typeOne = PokemonTypeModel.Grass,
    typeTwo = PokemonTypeModel.Poison,
)

val Ivysaur = PokemonEvolutionModel(
    numberName = "Nº002",
    name = "ivysaur",
    id = 2,
    typeOne = PokemonTypeModel.Grass,
    typeTwo = PokemonTypeModel.Poison,
)

val Venusaur = PokemonEvolutionModel(
    numberName = "Nº003",
    name = "venusaur",
    id = 3,
    typeOne = PokemonTypeModel.Grass,
    typeTwo = PokemonTypeModel.Poison,
)
