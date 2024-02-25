package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.TextLabel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelLarge
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelSmall

@Composable
fun PokemonFeature(
    @DrawableRes iconId: Int,
    @StringRes labelTextId: Int,
    infoText: String,
    modifier: Modifier = Modifier,
) {
    PokemonFeatureView(
        iconId = iconId,
        labelTextId = labelTextId,
        infoText = infoText,
        modifier = modifier,
    )
}

@Composable
private fun PokemonFeatureView(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int = R.drawable.ic_weight,
    @StringRes labelTextId: Int = R.string.pokemon_feature_label_weight,
    infoText: String = "6,9kg",
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.size(PaddingOne))
            TextLabel(
                text = stringResource(id = labelTextId).uppercase(),
                fontSize = TextLabelSmall,
            )
        }
        Spacer(modifier = Modifier.size(size = PaddingHalf))
        PokemonName(
            text = infoText,
            fontSize = TextLabelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    shape = RoundedCornerShape(15.dp),
                    width = 1.dp,
                    color = Black.copy(alpha = 0.1f),
                )
                .padding(all = PaddingOne)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonFeaturePreview() {
    PokemonFeatureView()


}