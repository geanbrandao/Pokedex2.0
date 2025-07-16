package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.common.toColor
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTypography
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeLargeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeSmallSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne

//@Composable
//fun PokemonTypeSmallView(
//    type: PokemonTypeModel,
//    modifier: Modifier = Modifier,
//) {
//    PokemonType(
//        type = type,
//        modifier = modifier,
//        iconSize = IconTypeSmallSize,
//    )
//}

//@Composable
//fun PokemonTypeLargeView(
//    type: PokemonTypeModel,
//    modifier: Modifier = Modifier,
//) {
//    PokemonType(
//        type = type,
//        modifier = modifier,
//        iconSize = IconTypeLargeSize,
////        fontSize = TextBodyLarge,
//    )
//}

@Composable
fun PokemonType(
    modifier: Modifier = Modifier,
    type: PokemonTypeModel,
    iconSize: Dp,
    style: TextStyle,
) {
    Row(
        modifier = modifier
            .background(color = type.color.toColor(), shape = CardDefaults.shape)
            .padding(vertical = PaddingHalf, horizontal = PaddingOne),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.background(Color.White, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = type.icon),
                tint = type.color.toColor(),
                contentDescription = "grass icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(2.5.dp)
                    .size(size = iconSize)
            )
        }
        Spacer(modifier = Modifier.size(size = PaddingOne))
        Text(
            text = stringResource(id = type.name),
            style = style,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypePreview(
    @PreviewParameter(PokemonTypePreviewProvider::class) item: PokemonTypeModelPreview
) {
    AppTheme {
        PokemonType(
            type = item.type,
            iconSize = item.iconSize,
            style = item.style,
        )
    }
}

private data class PokemonTypeModelPreview(
    val type: PokemonTypeModel,
    val iconSize: Dp,
    val style: TextStyle,
)

private class PokemonTypePreviewProvider : PreviewParameterProvider<PokemonTypeModelPreview> {
    override val values: Sequence<PokemonTypeModelPreview>
        get() = sequenceOf(
            PokemonTypeModelPreview(
                type = PokemonTypeModel.Grass,
                iconSize = IconTypeSmallSize,
                style = AppTypography.bodyMedium,
            ),
            PokemonTypeModelPreview(
                type = PokemonTypeModel.Dragon,
                iconSize = IconTypeSmallSize,
                style = AppTypography.bodyMedium,
            ),
            PokemonTypeModelPreview(
                type = PokemonTypeModel.Electric,
                iconSize = IconTypeLargeSize,
                style = AppTypography.bodyLarge,
            ),
            PokemonTypeModelPreview(
                type = PokemonTypeModel.Flying,
                iconSize = IconTypeLargeSize,
                style = AppTypography.bodyLarge,
            ),
        )
}