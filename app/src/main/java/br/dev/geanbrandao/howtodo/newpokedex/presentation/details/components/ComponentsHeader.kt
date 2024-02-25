package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.common.clickableNoRippleEffect
import br.dev.geanbrandao.howtodo.newpokedex.common.gradient45
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.Bulbasaur
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.Rayquaza
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.debugPlaceholder
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.DetailsPokeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.DragonColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HeaderView(
    pokemon: PokemonModel
) {
    Header(pokemon = pokemon)
}

@Composable
private fun Header(
    pokemon: PokemonModel = Rayquaza.pokemon,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (circleRef, iconRef, imgRef) = createRefs()
        val guidelineReference = createGuidelineFromTop(200.dp)
        Box(
            modifier = Modifier
                .size(500.dp)
                .background(shape = CircleShape, color = pokemon.typeOne.color)
                .constrainAs(circleRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.top)
                }
        )
        Icon(
            painter = painterResource(id = pokemon.typeOne.icon),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            brush = pokemon.typeOne.color.gradient45,
                            blendMode = BlendMode.SrcAtop
                        )
                    }
                }
                .size(200.dp)
                .constrainAs(iconRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(circleRef.bottom)
                }
        )
        PokemonSprite(
            Modifier
                .constrainAs(imgRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(guidelineReference)
                    bottom.linkTo(guidelineReference)
                },
            pokemon = pokemon,
        )
    }
}


@Composable
fun TopHeaderView(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
) {
//    val isFav = remember { mutableStateOf(false) }
//
//    val (favIconId, favIconContentDescription) = if (isFav.value) {
//        Pair(
//            R.drawable.ic_heart_simple_fill,
//            R.string.content_description_icon_add_favorite,
//        )
//    } else {
//        Pair(
//            R.drawable.ic_heart_simple,
//            R.string.content_description_icon_remove_favorite,
//        )
//    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingTwo, vertical = PaddingOne),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.content_description_icon_back),
            tint = Color.White,
            modifier = Modifier.clickableNoRippleEffect{ onBackPressed() }
        )
//        Icon(
//            painter = painterResource(id = favIconId),
//            contentDescription = stringResource(id = favIconContentDescription),
//            tint = Color.Unspecified,
//            modifier = Modifier
//                .clickableNoRippleEffect { isFav.value = isFav.value.not() }
//        )
    }
}


@Composable
private fun PokemonSprite(
    modifier: Modifier = Modifier,
    pokemon: PokemonModel = Bulbasaur.pokemon,
) {

    val currentImgUrl = remember {
        mutableStateOf(pokemon.imgUrlNormal)
    }

    val coroutineScope = rememberCoroutineScope()

    AsyncImage(
        model = currentImgUrl.value,
        placeholder = debugPlaceholder(R.drawable.bulbasaur_1),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(DetailsPokeSize)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        coroutineScope.launch {
                            currentImgUrl.value = pokemon.imgUrlShiny
                            delay(6_000)
                            currentImgUrl.value = pokemon.imgUrlNormal
                        }
                    }
                )
            }
    )
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview
@Composable
fun TopHeaderPreview() {
    TopHeaderView(
        modifier = Modifier.background(color = DragonColor)
    )
}