package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonTypeLargeView
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelLarge

@Composable
fun PokemonWeakness(
    list: List<PokemonTypeModel>,
    modifier: Modifier = Modifier,
) {
    PokemonWeaknessView(modifier = modifier, list = list)
}

@Composable
private fun PokemonWeaknessView(
    modifier: Modifier = Modifier,
    list: List<PokemonTypeModel> = listOf(
        PokemonTypeModel.Water,
        PokemonTypeModel.Grass,
        PokemonTypeModel.Poison
    ),
) {
    Column(modifier = modifier) {
        PokemonName(
            text = stringResource(R.string.pokemon_details_label_weakness),
            fontSize = TextLabelLarge,
        )
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        for (i in list.indices step 2) {
            Column {
                Row {
                    val current = list[i]
                    val next = list.getOrNull(i+1)
                    PokemonTypeLargeView(
                        modifier = Modifier.weight(1f),
                        type = current
                    )
                    next?.let {
                        Spacer(modifier = Modifier.size(PaddingTwo))
                        PokemonTypeLargeView(
                            modifier = Modifier.weight(1f),
                            type = next
                        )
                    }
                }
                if (i != list.lastIndex) {
                    Spacer(modifier = Modifier.size(PaddingOne))
                }
            }
        }
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(count = 2),
//            verticalArrangement = Arrangement.spacedBy(space = PaddingOne),
//            horizontalArrangement = Arrangement.spacedBy(space = PaddingOne),
//        ) {
//            items(items = list) {
//                PokemonTypeLargeView(type = it)
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonWeaknessViewPreview() {
    PokemonWeaknessView()
}