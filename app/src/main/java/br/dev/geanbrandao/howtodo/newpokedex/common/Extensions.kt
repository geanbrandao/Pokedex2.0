package br.dev.geanbrandao.howtodo.newpokedex.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeEnum
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Bug
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Dark
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Dragon
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Electric
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Fairy
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Fighting
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Fire
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Flying
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Ghost
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Grass
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Ground
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Ice
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Normal
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Poison
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Psychic
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Rock
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Steel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel.Water

val Color.gradient45
    get() = Brush.linearGradient(
        colors = listOf(Color.White, this),
        start = Offset.Zero,
        end = Offset.Infinite
    )

fun Modifier.shimmerEffect(
    shape: Shape = RectangleShape
): Modifier = composed {
    val size = remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "shimmer effect")
    val startOffsetX = transition.animateFloat(
        initialValue = -2 * size.value.width.toFloat(),
        targetValue = 2 * size.value.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ), label = "offset animation"
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFCCCCCC),
                Color(0xFF999999),
                Color(0xFFCCCCCC),
            ),
            start = Offset(x = startOffsetX.value, y = 0f),
            end = Offset(
                x = startOffsetX.value + size.value.width.toFloat(),
                y = size.value.height.toFloat()
            )
        ),
        shape = shape,
    ).onGloballyPositioned { size.value = it.size }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.clickableNoRippleEffect(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}


@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }


@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

fun PokemonTypeModel.getPokemonTypeWeaknesses(): List<PokemonTypeModel> {
    return when (this) {
        is Normal -> listOf(Fighting)
        is Fire -> listOf(Water, Rock, Ground)
        is Water -> listOf(Electric, Grass)
        is Electric -> listOf(Ground)
        is Grass -> listOf(Fire, Ice, Poison, Flying, Bug)
        is Ice -> listOf(Fire, Fighting, Rock, Steel)
        is Fighting -> listOf(Psychic, Flying, Fairy)
        is Poison -> listOf(Ground, Psychic)
        is Ground -> listOf(Water, Grass, Ice)
        is Flying -> listOf(Electric, Ice, Rock)
        is Psychic -> listOf(Dark, Ghost, Bug)
        is Bug -> listOf(Fire, Flying, Rock)
        is Rock -> listOf(Water, Grass, Fighting, Ground, Steel)
        is Ghost -> listOf(Dark, Ghost)
        is Dragon -> listOf(Ice, Dragon, Fairy)
        is Dark -> listOf(Fighting, Bug, Fairy)
        is Steel -> listOf(Fire, Fighting, Ground)
        is Fairy -> listOf(Poison, Steel)
    }
}

fun Int.toFormattedString(): String {
    val formattedNumber = this.toString().padStart(3, '0')
    return "Nº$formattedNumber"
}


fun String.getTypeModel(): PokemonTypeModel? {
    return when (PokemonTypeEnum.from(this)) {
        PokemonTypeEnum.WATER -> Water
        PokemonTypeEnum.GRASS -> Grass
        PokemonTypeEnum.DRAGON -> Dragon
        PokemonTypeEnum.ELECTRIC -> Electric
        PokemonTypeEnum.FAIRY -> Fairy
        PokemonTypeEnum.GHOST -> Ghost
        PokemonTypeEnum.FIRE -> Fire
        PokemonTypeEnum.ICE -> Ice
        PokemonTypeEnum.BUG -> Bug
        PokemonTypeEnum.FIGHTING -> Fighting
        PokemonTypeEnum.NORMAL -> Normal
        PokemonTypeEnum.DARK -> Dark
        PokemonTypeEnum.STEEL -> Steel
        PokemonTypeEnum.ROCK -> Rock
        PokemonTypeEnum.PSYCHIC -> Psychic
        PokemonTypeEnum.GROUND -> Ground
        PokemonTypeEnum.POISON -> Poison
        PokemonTypeEnum.FLYING -> Flying
        else -> null
    }
}

fun Int.decimetresToMeters() = (this.toFloat() / 10)

fun String.getDigits() = this.filter { it.isDigit() }
