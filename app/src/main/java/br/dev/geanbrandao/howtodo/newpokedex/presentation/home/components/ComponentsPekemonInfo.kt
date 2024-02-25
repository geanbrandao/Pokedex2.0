package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonNumberName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.Bulbasaur
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelSmall
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextTitleLarge
import java.util.Locale

@Composable
fun InfoView(
    item: PokemonModel,
    modifier: Modifier = Modifier,
) {
    PokemonInfo(modifier = modifier, item = item)
}

@Composable
private fun PokemonInfo(
    modifier: Modifier = Modifier,
    item: PokemonModel = Bulbasaur.pokemon,
) {
    Column(
        modifier = modifier
    ) {
        PokemonNumberName(text = item.numberName, fontSize = TextLabelSmall)
        PokemonName(text = item.name, fontSize = TextTitleLarge)
        Spacer(modifier = Modifier.size(size = PaddingOne))
        PokemonTypesView(typeOne = item.typeOne, typeTwo = item.typeTwo, isLarge = false)
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    PokemonInfo()
}

fun String.capitalize() = this.replaceFirstChar {
    if (it.isLowerCase()) {
        it.titlecase(Locale.getDefault())
    } else {
        it.toString()
    }
}