package br.dev.geanbrandao.howtodo.newpokedex.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.common.clickableNoRippleEffect
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.ErrorScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.destinations.PokemonDetailsScreenDestination
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonCardView
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonShimmerItem
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonDetailsModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun PokemonListScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigator: DestinationsNavigator,
) {
    val uiState = viewModel.uiState.collectAsState()
    val pokemonList = uiState.value.pokemonList
    val isLoading = uiState.value.isLoading


    if (uiState.value.error != null) {
        ErrorScreen {
            viewModel.onTryAgain()
        }
    }

    PokemonListView(
        pokemonList = pokemonList,
        isLoading = isLoading,
        navigateToDetails = { item: PokemonModel ->
            navigator.navigate(PokemonDetailsScreenDestination(pokemon = item))
        },
    )
}

@Composable
private fun PokemonListView(
    pokemonList: List<PokemonModel>,
    isLoading: Boolean = false,
    navigateToDetails: (PokemonModel) -> Unit = {},
) {
    val listState = rememberLazyListState()

    Column(modifier = Modifier.background(color = Color.White)) {
        LazyColumn(
            contentPadding = PaddingValues(all = PaddingOne),
            state = listState,
        ) {
            items(
                items = pokemonList,
                key = {
                    it.id
                }
            ) {
                PokemonCardView(
                    item = it,
                    modifier = Modifier
                        .padding(bottom = PaddingTwo)
                        .clickableNoRippleEffect {
                            navigateToDetails(it)
                        }
                )
            }
            if (isLoading) {
                item {
                    PokemonShimmerItem()
                    PokemonShimmerItem()
                }
            }
        }

    }
}

@Preview
@Composable
private fun PokemonListPreview() {
    val list = listOf(
        Bulbasaur,
        Ivysaur,
        Venusaur,
        Charmander,
        Squirtle,
        Ditto,
        Pikachu,
        Clefairy,
        Onix,
        Koffing,
        Mew,
        Suicune,
        Rayquaza,
        Toucannon,
    ).map { it.pokemon }
    PokemonListView(pokemonList = list)
}

val Bulbasaur = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº001",
        name = "bulbasaur",
        id = 1,
        typeOne = PokemonTypeModel.Grass,
        typeTwo = PokemonTypeModel.Poison,
        abilities = listOf(),
        stats =  listOf(
            PokemonModel.Stat("HP", 65),
            PokemonModel.Stat("Atacck", 10),
            PokemonModel.Stat("Defense", 50),
        ),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Ivysaur = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº002",
        name = "ivysaur",
        id = 2,
        typeOne = PokemonTypeModel.Grass,
        typeTwo = PokemonTypeModel.Poison,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Venusaur = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº003",
        name = "venusaur",
        id = 3,
        typeOne = PokemonTypeModel.Grass,
        typeTwo = PokemonTypeModel.Poison,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Charmander = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº004",
        name = "charmander",
        id = 4,
        typeOne = PokemonTypeModel.Fire,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Squirtle = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº007",
        name = "squirtle",
        id = 7,
        typeOne = PokemonTypeModel.Water,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Pikachu = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº025",
        name = "pikachu",
        id = 25,
        typeOne = PokemonTypeModel.Electric,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Clefairy = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº035",
        name = "clefairy",
        id = 35,
        typeOne = PokemonTypeModel.Fairy,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Onix = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº095",
        name = "onix",
        id = 95,
        typeOne = PokemonTypeModel.Rock,
        typeTwo = PokemonTypeModel.Ground,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Koffing = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº109",
        name = "koffing",
        id = 109,
        typeOne = PokemonTypeModel.Poison,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Mew = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº151",
        name = "mew",
        id = 151,
        typeOne = PokemonTypeModel.Psychic,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Suicune = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº245",
        name = "suicune",
        id = 245,
        typeOne = PokemonTypeModel.Water,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Rayquaza = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº384",
        name = "rayquaza",
        id = 384,
        typeOne = PokemonTypeModel.Dragon,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Toucannon = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº733",
        name = "toucannon",
        id = 733,
        typeOne = PokemonTypeModel.Flying,
        typeTwo = PokemonTypeModel.Normal,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)

val Ditto = PokemonDetailsModel(
    pokemon = PokemonModel(
        numberName = "Nº132",
        name = "ditto",
        id = 132,
        typeOne = PokemonTypeModel.Normal,
        abilities = listOf(),
        stats = listOf(),
        height = 0.7f,
        weight = 6.9f,
    ),
    genderRate = 1,
    generation = "generation-i",
    evolutionUrl = "",
)