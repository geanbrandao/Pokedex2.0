package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonType
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeLargeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo

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
        Text(
            text = stringResource(R.string.pokemon_details_label_weakness),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        for (i in list.indices step 2) {
            Column {
                Row {
                    val current = list[i]
                    val next = list.getOrNull(i + 1)
                    PokemonType(
                        modifier = Modifier.weight(1f),
                        type = current,
                        iconSize = IconTypeLargeSize,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    next?.let {
                        Spacer(modifier = Modifier.size(PaddingTwo))
                        PokemonType(
                            modifier = Modifier.weight(1f),
                            type = next,
                            iconSize = IconTypeLargeSize,
                            style = MaterialTheme.typography.bodyLarge,
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