package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne

@Composable
fun PokemonTypesView(
    typeOne: PokemonTypeModel,
    typeTwo: PokemonTypeModel?,
    isLarge: Boolean,
    modifier: Modifier = Modifier,
) {
    PokemonTypes(typeOne = typeOne, typeTwo = typeTwo, isLarge = isLarge, modifier = modifier)
}

@Composable
private fun PokemonTypes(
    modifier: Modifier = Modifier,
    typeOne: PokemonTypeModel = PokemonTypeModel.Grass,
    typeTwo: PokemonTypeModel? = PokemonTypeModel.Poison,
    isLarge: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        PokemonTypeView(type = typeOne, isLarge = isLarge)
        Spacer(modifier = Modifier.size(size = PaddingOne))
        typeTwo?.let {
            PokemonTypeView(type = it, isLarge = isLarge)
        }
    }
}

@Composable
fun PokemonTypeView(
    type: PokemonTypeModel,
    isLarge: Boolean,
) {
    if (isLarge) {
        PokemonTypeLargeView(type = type)
    } else {
        PokemonTypeSmallView(type = type)
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypesPreview() {
    Column {
        PokemonTypes()
        PokemonTypes(isLarge = true)
    }
}