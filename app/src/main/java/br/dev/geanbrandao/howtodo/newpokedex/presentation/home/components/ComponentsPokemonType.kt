package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonTypeName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeLargeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeSmallSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextBodyLarge
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelSmall

@Composable
fun PokemonTypeSmallView(
    type: PokemonTypeModel,
    modifier: Modifier = Modifier,
) {
    PokemonType(
        type = type,
        modifier = modifier,
        iconSize = IconTypeSmallSize,
        fontSize = TextLabelSmall,
    )
}

@Composable
fun PokemonTypeLargeView(
    type: PokemonTypeModel,
    modifier: Modifier = Modifier,
) {
    PokemonType(
        type = type,
        modifier = modifier,
        iconSize = IconTypeLargeSize,
        fontSize = TextBodyLarge,
    )
}

@Composable
private fun PokemonType(
    modifier: Modifier = Modifier,
    type: PokemonTypeModel = PokemonTypeModel.Fairy,
    iconSize: Dp = IconTypeSmallSize,
    fontSize: TextUnit = TextLabelSmall,
) {
    Row(
        modifier = modifier
            .background(color = type.color, shape = RoundedCornerShape(48))
            .padding(vertical = PaddingHalf, horizontal = PaddingOne),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.background(Color.White, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = type.icon),
                tint = type.color,
                contentDescription = "grass icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(2.5.dp)
                    .size(size = iconSize)
            )
        }
        Spacer(modifier = Modifier.size(size = PaddingOne))
        PokemonTypeName(textId = type.name, fontSize = fontSize)
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypePreview() {
    Column {
        PokemonType()
        PokemonType(type = PokemonTypeModel.Grass)
    }
}