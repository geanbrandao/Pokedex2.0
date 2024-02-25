package br.dev.geanbrandao.howtodo.newpokedex.presentation.common

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelSmall
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Typography

@Composable
fun PokemonName(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    color: Color = Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    PokemonNameView(
        text = text,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
private fun PokemonNameView(
    modifier: Modifier = Modifier,
    text: String = "Bulbasaur",
    fontSize: TextUnit = 22.sp,
    color: Color = Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text.capitalize(),
        style = Typography.titleLarge,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun PokemonNumberName(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    color: Color = Black,
) {
    PokemonNumberNameView(
        text = text,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
    )
}

@Composable
private fun PokemonNumberNameView(
    modifier: Modifier = Modifier,
    text: String = "Nº001",
    fontSize: TextUnit = 12.sp,
    color: Color = Black,
) {
    Text(
        text = text,
        style = Typography.titleLarge,
        fontSize = fontSize,
        color = color.copy(alpha = 0.7f),
        modifier = modifier,
    )
}

@Composable
fun PokemonTypeName(
    @StringRes textId: Int,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    color: Color = Black,
) {
    PokemonTypeView(
        textId = textId,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
    )
}

@Composable
private fun PokemonTypeView(
    modifier: Modifier = Modifier,
    @StringRes textId: Int = R.string.pokemon_type_grass,
    fontSize: TextUnit = 12.sp,
    color: Color = Black,
) {
    Text(
        text = stringResource(id = textId),
        style = Typography.labelSmall,
        fontSize = fontSize,
        color = color.copy(alpha = 0.85f),
        modifier = modifier,
    )
}

@Composable
fun TextLabel(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    color: Color = Black,
) {
    TextLabelView(
        text = text,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
    )
}

@Composable
private fun TextLabelView(
    modifier: Modifier = Modifier,
    text: String = "Gênero",
    fontSize: TextUnit = TextLabelSmall,
    color: Color = Black,
) {
    Text(
        text = text,
        style = Typography.labelSmall,
        fontSize = fontSize,
        color = color.copy(alpha = 0.65f),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun PokemonNamePreview() {
    PokemonNameView()
}

@Preview(showBackground = true)
@Composable
fun PokemonNumberNamePreview() {
    PokemonNumberNameView()
}

@Preview(showBackground = true)
@Composable
private fun PokemonTypePreview() {
    PokemonTypeView()
}

@Preview(showBackground = true)
@Composable
private fun TextLabelPreview() {
    TextLabelView()
}