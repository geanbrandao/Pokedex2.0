package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.common.clickableNoRippleEffect
import br.dev.geanbrandao.howtodo.newpokedex.common.gradient45
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import coil.compose.AsyncImage

@Composable
fun PokeView(
    type: PokemonTypeModel,
    pokeUrl: String,
    modifier: Modifier = Modifier,
) {
    Poke(modifier = modifier, type = type, pokeUrl = pokeUrl)
}

@Composable
private fun Poke(
    modifier: Modifier = Modifier,
    type: PokemonTypeModel = PokemonTypeModel.Grass,
    pokeUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
) {

    val isFav = remember { mutableStateOf(false) }

    val (favIconId, favIconContentDescription) = if (isFav.value) {
        Pair(
            R.drawable.ic_heart_fill,
            R.string.content_description_icon_add_favorite,
        )
    } else {
        Pair(
            R.drawable.ic_heart,
            R.string.content_description_icon_remove_favorite,
        )
    }

    Column(
        modifier = modifier
            .background(color = type.color, RoundedCornerShape(percent = 15))
            .padding(all = PaddingOne)
            .size(size = 100.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (bgRef, imgRef, favRef) = createRefs()

            Icon(
                painter = painterResource(id = type.icon),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush = type.color.gradient45, blendMode = BlendMode.SrcAtop)
                        }
                    }
                    .constrainAs(bgRef) {
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            AsyncImage(
                model = pokeUrl,
                placeholder = debugPlaceholder(R.drawable.bulbasaur_1),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(70.dp)
                    .constrainAs(imgRef) {
//                        width = Dimension.fillToConstraints
//                        height = Dimension.fillToConstraints
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Icon(
                painter = painterResource(id = favIconId),
                contentDescription = stringResource(id = favIconContentDescription),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clickableNoRippleEffect { isFav.value = isFav.value.not() }
                    .constrainAs(favRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            )
//            Image(
//                painter = painterResource(id = R.drawable.bulbasaur_1),
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier
//                    .size(85.dp)
//                    .padding(all = PaddingThree)
//                    .constrainAs(imgRef) {
//                        width = Dimension.fillToConstraints
//                        height = Dimension.fillToConstraints
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                    }
//            )
        }

    }
}

//val gradient45 = Brush.linearGradient(
//    colors = listOf(Color.White, Color.White.copy(alpha = 0.9f)),
//    start = Offset(0f, Float.POSITIVE_INFINITY),
//    end = Offset(Float.POSITIVE_INFINITY, 0f)
//)



//fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
//    Modifier.drawBehind {
//        val angleRad = angle / 180f * PI
//        val x = cos(angleRad).toFloat() //Fractional x
//        val y = sin(angleRad).toFloat() //Fractional y
//
//        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
//        val offset = center + Offset(x * radius, y * radius)
//
//        val exactOffset = Offset(
//            x = min(offset.x.coerceAtLeast(0f), size.width),
//            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
//        )
//
//        drawRect(
//            brush = Brush.linearGradient(
//                colors = colors,
//                start = Offset(size.width, size.height) - exactOffset,
//                end = exactOffset
//            ),
//            size = size
//        )
//    }
//)

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

@Preview(showBackground = true)
@Composable
private fun PokePreview() {
    Poke()
}