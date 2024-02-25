package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.Bulbasaur
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo


@Composable
fun PokemonCardView(
    item: PokemonModel,
    modifier: Modifier = Modifier,
) {
    PokemonCard(modifier = modifier, item = item)
}

@Composable
private fun PokemonCard(
    modifier: Modifier = Modifier,
    item: PokemonModel = Bulbasaur.pokemon,
) {
    Row(
        modifier = modifier
            .background(
                color = item.typeOne.color.copy(alpha = 0.15f),
                shape = RoundedCornerShape(percent = 15)
            )
            .padding(start = PaddingTwo),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoView(
            item = item,
            modifier = Modifier.padding(top = PaddingTwo, bottom = PaddingTwo)
        )
        Spacer(modifier = Modifier.size(size = PaddingTwo).weight(weight = 1f))
        PokeView(type = item.typeOne, pokeUrl = item.imgUrlNormal)
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonItemPreview() {
    Column(Modifier.fillMaxWidth()) {
        PokemonCard(Modifier.fillMaxWidth())
    }
}
