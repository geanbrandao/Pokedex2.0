package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.PokemonPreviewProvider
import br.dev.geanbrandao.howtodo.newpokedex.common.toColor
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo

@Composable
fun PokemonCard(
    item: PokemonV2,
    modifier: Modifier = Modifier,
    onHeartClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(
                color = item.typeOne.color.toColor().copy(alpha = 0.15f),
                shape = RoundedCornerShape(percent = 15)
            )
            .padding(start = PaddingTwo),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokemonBasicInfo(
            item = item,
            modifier = Modifier.padding(top = PaddingTwo, bottom = PaddingTwo)
        )
        Spacer(modifier = Modifier.size(size = PaddingTwo).weight(weight = 1f))
        PokeView(
            type = item.typeOne,
            pokeUrl = item.imgUrlNormal,
            isFavorite = item.isFavorite,
            onHeartClicked = onHeartClicked)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview(
    @PreviewParameter(PokemonPreviewProvider::class) item: PokemonV2,
) {
    AppTheme {
        PokemonCard(item = item, onHeartClicked = {})
    }
}
