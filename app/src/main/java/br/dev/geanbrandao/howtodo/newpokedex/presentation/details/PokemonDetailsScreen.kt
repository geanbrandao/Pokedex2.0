package br.dev.geanbrandao.howtodo.newpokedex.presentation.details

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.common.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.common.clickableNoRippleEffect
import br.dev.geanbrandao.howtodo.newpokedex.common.gradient45
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.PokemonDetailsPreviewProvider
import br.dev.geanbrandao.howtodo.newpokedex.common.toColor
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.ErrorScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.HeaderState.Collapsed
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.HeaderState.Expanded
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components.PokemonDetailsInfo
import br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components.PokemonSprite
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonType
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeLargeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeSmallSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun PokemonDetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    pokemonId: Int,
) {
    val uiState = viewModel.uiState.collectAsState()
    if (uiState.value.error != null) {
        ErrorScreen {
            viewModel.getPokemonDetails(pokemonId)
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getPokemonDetails(pokemonId)
        }
    }

    uiState.value.pokemon?.let {
        PokemonDetailsView(
            item = it,
            onBackPressed = {
                viewModel.navigateBack()
            },
            onHeartClicked = {
                viewModel.onHeartClicked(pokemonId)
            }
        )
    }
}

@Composable
private fun PokemonDetailsView(
    item: PokemonV2Details,
    onBackPressed: () -> Unit,
    onHeartClicked: () -> Unit,
) {
    val scrollState = rememberScrollState()

    val density = LocalDensity.current

    val maxHeight = 300.dp
    val minHeight = 86.dp
    var currentHeightPx by remember { mutableIntStateOf(0) }
    val currentHeightDp = with(density) { currentHeightPx.toDp() }


    val maxScrollPx = with(density) { (maxHeight - minHeight).toPx() }

    val scrollY = remember { mutableFloatStateOf(0f) }
    // Caso tenha espaco disponivel para animacão, calcula a progressão
    val scrollProgress = if (currentHeightDp <= (maxHeight * 1.7f)) 1f else (scrollY.floatValue / maxScrollPx).coerceIn(0f, 1f)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val deltaY = available.y
                scrollY.floatValue = (scrollY.floatValue - deltaY).coerceIn(0f, maxScrollPx)
                return Offset.Zero
            }
        }
    }

    Column(
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                currentHeightPx = layoutCoordinates.size.height
            }
            .nestedScroll(nestedScrollConnection)
    ) {
        CollapsedHeaderV3(
            modifier = Modifier,
            scrollProgress = scrollProgress,
            pokemon = item.pokemon,
            onBackPressed = onBackPressed,
            onHeartClicked = onHeartClicked,
        )
        Column(
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            Spacer(modifier = Modifier.size(size = PaddingTwo))
            PokemonDetailsInfo(pokemon = item)
        }
    }
}

@Composable
fun CollapsedHeader(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    onBackPressed: () -> Unit,
    pokemon: PokemonV2,
) {
    val shouldCollapse by scrollState.rememberDebouncedScrollActivity() // true se scroll passou do limiar

    val height by animateDpAsState(
        targetValue = if (shouldCollapse) 82.dp else 300.dp,
        animationSpec = tween(300)
    )

    var boxWidthPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val boxWidthDp = with(density) { boxWidthPx.toDp() }

    val cornerRadius by animateDpAsState(
        targetValue = if (shouldCollapse) 0.dp else boxWidthDp / 2,
        animationSpec = tween(300)
    )

    val pokemonSize by animateDpAsState(
        targetValue = if (shouldCollapse) 50.dp else 200.dp,
        animationSpec = tween(300)
    )

    val typeSize by animateDpAsState(
        targetValue = if (shouldCollapse) 60.dp else 200.dp,
        animationSpec = tween(300)
    )

    val typeOffsetCollapsed = with(density) {
        IntOffset(
            x = (boxWidthDp - PaddingTwo - typeSize).toPx().roundToInt(),
            y = PaddingTwo.toPx().roundToInt(),
        )
    }
    val typeOffsetExpanded = with(density) {
        IntOffset(
            x = ((boxWidthDp / 2) - (typeSize / 2)).toPx().roundToInt(),
            y = (height - typeSize).toPx().roundToInt(),
        )
    }

    val typeOffset by animateIntOffsetAsState(
        targetValue = if (shouldCollapse) typeOffsetCollapsed else typeOffsetExpanded,
        animationSpec = tween(200),
    )

    val pokemonOffsetCollapsed = with(density) {
        IntOffset(
            x = (boxWidthDp - PaddingTwo - pokemonSize - 5.dp).toPx().roundToInt(),
            y = PaddingTwo.toPx().roundToInt(),
        )
    }
    val pokemonOffsetExpanded = with(density) {
        IntOffset(
            x = ((boxWidthDp / 2) - (pokemonSize / 2)).toPx().roundToInt(),
            y = (height - (pokemonSize * (2f / 3f))).toPx().roundToInt(),
        )
    }

    val pokemonOffset by animateIntOffsetAsState(
        targetValue = if (shouldCollapse) pokemonOffsetCollapsed else pokemonOffsetExpanded,
        animationSpec = tween(200),
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .onSizeChanged { size ->
                    boxWidthPx = size.width
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = cornerRadius
                    )
                )
                .background(pokemon.typeOne.color.toColor())
        )

        Icon(
            painter = painterResource(pokemon.typeOne.icon),
            contentDescription = null,
            tint = Color.White.copy(0.6f),
            modifier = Modifier
//                .graphicsLayer(alpha = 0.99f)
//                .drawWithCache {
//                    onDrawWithContent {
//                        drawContent()
//                        drawRect(
//                            brush = pokemon.typeOne.color.toColor().gradient45,
//                            blendMode = BlendMode.SrcAtop
//                        )
//                    }
//                }
                .size(typeSize)
                .offset { typeOffset }
        )

        PokemonSprite(
            pokemon = pokemon,
            size = pokemonSize,
            modifier = Modifier.offset { pokemonOffset }
        )

        IconButton(
            onClick = onBackPressed,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                )
            }
        )
    }
}

@Composable
fun CollapsedHeaderV2(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    onBackPressed: () -> Unit,
    pokemon: PokemonV2,
) {
    val shouldCollapse by scrollState.rememberDebouncedScrollActivityV2()
    val transition = updateTransition(targetState = shouldCollapse, label = "headerTransition")

    val density = LocalDensity.current
    var boxWidthPx by remember { mutableStateOf(0) }
    val boxWidthDp = with(density) { boxWidthPx.toDp() }

    val height by transition.animateDp(
        label = "backgroundHeight",
        transitionSpec = {
            if (Collapsed isTransitioningTo Expanded) {
                tween(durationMillis = 300)
            } else {
                tween(durationMillis = 300)
            }
        },
        targetValueByState = { state ->
            when (state) {
                Collapsed -> 86.dp
                Expanded -> 300.dp
            }
        }
    )

    val cornerRadius by transition.animateDp(
        label = "cornerRadius",
        transitionSpec = {
            if (Collapsed isTransitioningTo Expanded) {
                tween(durationMillis = 300)
            } else {
                tween(durationMillis = 300)
            }
        },
        targetValueByState = { state ->
            when (state) {
                Collapsed -> 0.dp
                Expanded -> boxWidthDp / 2
            }
        }
    )

    val pokemonSize by transition.animateDp(
        label = "pokemonSize",
        transitionSpec = {
            if (Collapsed isTransitioningTo Expanded) {
                tween(durationMillis = 300)
            } else {
                tween(durationMillis = 300)
            }
        },
        targetValueByState = { state ->
            when (state) {
                Collapsed -> 50.dp
                Expanded -> 200.dp
            }
        }
    )

    val typeSize by transition.animateDp(
        label = "typeSize",
        transitionSpec = {
            if (Collapsed isTransitioningTo Expanded) {
                tween(durationMillis = 300)
            } else {
                tween(durationMillis = 300)
            }
        },
        targetValueByState = { state ->
            when (state) {
                Collapsed -> 60.dp
                Expanded -> 200.dp
            }
        }
    )

    val typeOffsetCollapsed = with(density) {
        IntOffset(
            x = (boxWidthDp - PaddingTwo - typeSize).toPx().roundToInt(),
            y = PaddingTwo.toPx().roundToInt(),
        )
    }
    val typeOffsetExpanded = with(density) {
        IntOffset(
            x = ((boxWidthDp / 2) - (typeSize / 2)).toPx().roundToInt(),
            y = (height - typeSize).toPx().roundToInt(),
        )
    }

    val typeOffset by transition.animateIntOffset(
        label = "typeOffset",
        transitionSpec = {
            if (Collapsed isTransitioningTo Expanded) {
                tween(durationMillis = 300)
            } else {
                tween(durationMillis = 300)
            }
        },
        targetValueByState = { state ->
            when (state) {
                Collapsed -> typeOffsetCollapsed
                Expanded -> typeOffsetExpanded
            }
        }
    )

    val pokemonOffsetCollapsed = with(density) {
        IntOffset(
            x = (boxWidthDp - PaddingTwo - pokemonSize - 5.dp).toPx().roundToInt(),
            y = PaddingTwo.toPx().roundToInt(),
        )
    }
    val pokemonOffsetExpanded = with(density) {
        IntOffset(
            x = ((boxWidthDp / 2) - (pokemonSize / 2)).toPx().roundToInt(),
            y = (height - (pokemonSize * (2f / 3f))).toPx().roundToInt(),
        )
    }

    val pokemonOffset by transition.animateIntOffset(
        label = "pokemonOffset",
        transitionSpec = {
            if (Collapsed isTransitioningTo Expanded) {
                tween(durationMillis = 300)
            } else {
                tween(durationMillis = 300)
            }
        },
        targetValueByState = { state ->
            when (state) {
                Collapsed -> pokemonOffsetCollapsed
                Expanded -> pokemonOffsetExpanded
            }
        }
    )

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .graphicsLayer {
                    scaleX
                }
                .onSizeChanged { size ->
                    boxWidthPx = size.width
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = cornerRadius
                    )
                )
                .background(pokemon.typeOne.color.toColor())
        )

        Icon(
            painter = painterResource(pokemon.typeOne.icon),
            contentDescription = null,
            tint = Color.White.copy(0.6f),
            modifier = Modifier
//                .graphicsLayer(alpha = 0.99f)
//                .drawWithCache {
//                    onDrawWithContent {
//                        drawContent()
//                        drawRect(
//                            brush = pokemon.typeOne.color.toColor().gradient45,
//                            blendMode = BlendMode.SrcAtop
//                        )
//                    }
//                }
                .size(typeSize)
                .offset { typeOffset }
        )

        PokemonSprite(
            pokemon = pokemon,
            size = pokemonSize,
            modifier = Modifier.offset { pokemonOffset }
        )

        IconButton(
            onClick = onBackPressed,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                )
            }
        )
    }
}

@Composable
fun CollapsedHeaderV3(
    modifier: Modifier = Modifier,
    scrollProgress: Float,
    onBackPressed: () -> Unit,
    onHeartClicked: () -> Unit,
    pokemon: PokemonV2,
) {
    val density = LocalDensity.current

    var boxWidthPx by remember { mutableStateOf(0) }
    val boxWidthDp = with(density) { boxWidthPx.toDp() }

    val maxTypeSize = 200.dp
    val minTypeSize = 90.dp
    val differenceType = 10.dp
    val iconSize = 48.dp

    val maxPokemonSize = 200.dp
    val minPokemonSize = minTypeSize - differenceType

    val maxHeaderHeight = 300.dp
    val minHeaderHeight = (PaddingTwo * 2) + minTypeSize

    val bgPokemonHeight = minHeaderHeight
    val bgPokemonWithMax = boxWidthDp
    val bgPokemonWithMin = minHeaderHeight

    var spacerSizePx by remember { mutableStateOf(0) }
    val spacerSizeDp = with(density) { spacerSizePx.toDp() }

    val spacerFactorFloat = 0.2f

    val headerColor = lerp(
        start = pokemon.typeOne.color.toColor(),
        stop = pokemon.typeOne.color.toColor().copy(alpha = 0.15f),
        fraction = scrollProgress,
    )
    val headerHeight = lerp(start = maxHeaderHeight, stop = minHeaderHeight, fraction = scrollProgress)
    val cornerRadius = lerp(start = boxWidthDp / 2, stop = 0.dp, fraction = scrollProgress)
    val pokemonSize = lerp(start = maxPokemonSize, stop = minPokemonSize, fraction = scrollProgress)
    val typeSize = lerp(start = maxTypeSize, stop = minTypeSize, fraction = scrollProgress)
    val spacerSize = lerp(start = spacerSizeDp, stop = PaddingTwo, fraction = scrollProgress)
    val spacerFactor = lerp(start = spacerFactorFloat, stop = 0f, fraction = scrollProgress)
    val typeOffset = with(density) {
        lerp(
            start = IntOffset(
                x = ((boxWidthDp / 2) - (typeSize / 2)).toPx().roundToInt(),
                y = (headerHeight - typeSize).toPx().roundToInt()
            ),
            stop = IntOffset(
                x = (boxWidthDp - PaddingTwo - typeSize).toPx().roundToInt(),
                y = PaddingTwo.toPx().roundToInt()
            ),
            fraction = scrollProgress,
        )
    }
    val pokemonOffset = with(density) {
        lerp(
            start = IntOffset(
                x = ((boxWidthDp / 2) - (pokemonSize / 2)).toPx().roundToInt(),
                y = (headerHeight - (pokemonSize * (3f / 4f))).toPx().roundToInt()
            ),
            stop = IntOffset(
                x = (boxWidthDp - PaddingTwo - pokemonSize - (differenceType / 2)).toPx().roundToInt(),
                y = (PaddingTwo + (differenceType / 2)).toPx().roundToInt()
            ),
            fraction = scrollProgress,
        )
    }
    val infoOffset = with(density) {
        lerp(
            start = IntOffset(
                x = 0,
                y = (headerHeight).toPx().roundToInt(),
            ),
            stop = IntOffset(
                x = 0,
                y = iconSize.toPx().roundToInt(),
            ),
            fraction = scrollProgress,
        )
    }
    val bgPokemonWidth = lerp(start = bgPokemonWithMax, stop = bgPokemonWithMin, fraction = scrollProgress)
    val bgCornerRadiusPercent = lerp(start = 0, stop = 15, fraction = scrollProgress)
    val bgPokemonOffset = with(density) {
        lerp(
            start = IntOffset(x = 0, y = 0),
            stop = IntOffset(x = (boxWidthDp - bgPokemonHeight).toPx().roundToInt(), y = 0),
            fraction = scrollProgress,
        )
    }
    val bgPokemonColor = lerp(
        start = pokemon.typeOne.color.toColor().copy(alpha = 0.15f),
        stop = pokemon.typeOne.color.toColor(),
        fraction = scrollProgress,
    )

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                        .onSizeChanged { size ->
                            boxWidthPx = size.width
                        }
                        .clip(RoundedCornerShape(bottomStart = cornerRadius, bottomEnd = cornerRadius))
                        .background(headerColor)
                )
                Box(
                    modifier = Modifier
                        .height(bgPokemonHeight)
                        .width(bgPokemonWidth)
                        .offset { bgPokemonOffset }
                        .background(
                            color = bgPokemonColor,
                            shape = RoundedCornerShape(
                                topStartPercent = bgCornerRadiusPercent,
                                bottomStartPercent = bgCornerRadiusPercent
                            )
                        )
                )

            }
            Spacer(Modifier.size(size = spacerSize))
        }

        Icon(
            painter = painterResource(pokemon.typeOne.icon),
            contentDescription = null,
            tint = Color.White.copy(0.6f),
            modifier = Modifier
                .offset { typeOffset }
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            brush = pokemon.typeOne.color.toColor().gradient45,
                            blendMode = BlendMode.SrcAtop
                        )
                    }
                }
                .size(typeSize)
        )

        PokemonSprite(
            pokemon = pokemon,
            size = pokemonSize,
            modifier = Modifier.offset { pokemonOffset }
        )

        HeaderButtons(
            isFavorite = pokemon.isFavorite,
            onBackPressed = onBackPressed,
            onHeartClicked = onHeartClicked,
        )

        Column(
            modifier = Modifier
                .padding(start = PaddingTwo)
                .onSizeChanged { size ->
                    spacerSizePx = size.height
                }
                .offset { infoOffset }
        ) {

            PokemonBasicInfo(
                pokemon = pokemon,
                scrollProgress = scrollProgress,
                pokemonSize = pokemonSize,
                spacerFactor = spacerFactor
            )
        }
    }
}

@Composable
fun HeaderButtons(
    isFavorite: Boolean,
    onBackPressed: () -> Unit,
    onHeartClicked: () -> Unit,
) {
    val (favIconId, favIconContentDescription) = if (isFavorite) {
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

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            onClick = onBackPressed,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                )
            }
        )
        IconButton(
            onClick = onHeartClicked,
            content = {
                Icon(
                    painter = painterResource(id = favIconId),
                    contentDescription = stringResource(id = favIconContentDescription),
                    tint = Color.Unspecified,
                    modifier = Modifier.clickableNoRippleEffect { onHeartClicked() }
                )
            }
        )
    }
}

@Composable
fun PokemonBasicInfo(
    pokemon: PokemonV2,
    scrollProgress: Float,
    pokemonSize: Dp,
    spacerFactor: Float
) {
    val numberStyle = lerp(
        start = MaterialTheme.typography.titleLarge,
        stop = MaterialTheme.typography.bodyLarge,
        fraction = scrollProgress,
    )
    val nameStyle = lerp(
        start = MaterialTheme.typography.titleLarge,
        stop = MaterialTheme.typography.bodyLarge,
        fraction = scrollProgress,
    )
    val spacerSize = lerp(
        start = PaddingTwo,
        stop = PaddingOne,
        fraction = scrollProgress,
    )
    val typeIconSize = lerp(
        start = IconTypeLargeSize,
        stop = IconTypeSmallSize,
        fraction = scrollProgress,
    )
    val typeTextStyle = lerp(
        start = MaterialTheme.typography.bodyLarge,
        stop = MaterialTheme.typography.labelMedium,
        fraction = scrollProgress
    )

    Spacer(Modifier.size(pokemonSize * (spacerFactor)))
    Text(text = pokemon.name.capitalize(), style = nameStyle)
    Text(text = pokemon.numberFormatted, style = numberStyle)
    Spacer(modifier = Modifier.size(spacerSize))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PokemonType(
            type = pokemon.typeOne,
            iconSize = typeIconSize,
            style = typeTextStyle,
        )
        pokemon.typeTwo?.let {
            Spacer(modifier = Modifier.size(PaddingOne))
            PokemonType(
                type = it,
                iconSize = typeIconSize,
                style = typeTextStyle,
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun CollapsingHeaderPreview() {
    val maxHeight = 300.dp
    val minHeight = 86.dp
    val maxScrollPx = with(LocalDensity.current) { (maxHeight - minHeight).toPx() }

    val scrollY = remember { mutableFloatStateOf(0f) }

    val scrollProgress = (scrollY.floatValue / maxScrollPx).coerceIn(0f, 1f)

    var boxWidthPx by remember { mutableStateOf(0) }
    val boxWidthDp = with(LocalDensity.current) { boxWidthPx.toDp() }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val deltaY = available.y
                scrollY.floatValue = (scrollY.floatValue - deltaY).coerceIn(0f, maxScrollPx)
                return Offset.Zero
            }
        }
    }

    val height = lerp(maxHeight, minHeight, scrollProgress)
    val corner = lerp(boxWidthDp / 2, 0.dp, scrollProgress)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .onSizeChanged { boxWidthPx = it.width }
                .clip(RoundedCornerShape(bottomStart = corner, bottomEnd = corner))
                .background(Color(0xFF4CAF50))
        )

        // LISTA
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = height) // empurra a lista para baixo do header
        ) {
            items(30) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun CollapsingBoxWithScroll(
    modifier: Modifier = Modifier,
    scrollProgress: Float, // de 0f (expandido) até 1f (colapsado)
    pokemonColor: Color,
    boxWidthDp: Dp,
) {
    val height = lerp(300.dp, 86.dp, scrollProgress)
    val cornerRadius = lerp(boxWidthDp / 2, 0.dp, scrollProgress)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .onSizeChanged { size ->
                // atualizar largura se necessário
            }
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius
                )
            )
            .background(pokemonColor)
    )
}

/*
//    val transition = updateTransition(shouldCollapse, label = "collapsedHeader")
//    val height by transition.animateDp(
//        label = "",
//        transitionSpec = {
//            if (true isTransitioningTo false) {
//                tween(durationMillis = 300, easing = EaseOut)
//            } else {
//                tween(durationMillis = 300, easing = EaseIn)
//            }
//        }
//    ) { state ->
//        if (state) 82.dp else 250.dp
//    }
//    val cornerRadius by transition.animateDp(
//        label = "",
//        transitionSpec = {
//            if (true isTransitioningTo false) {
//                tween(durationMillis = 300, easing = EaseOut)
//            } else {
//                tween(durationMillis = 300, easing = EaseIn)
//            }
//        }
//    ) { state ->
//        if (state) 0.dp else boxWidthDp / 2
//    }
     */


@Composable
private fun ScrollState.rememberIsScrollingDown(): Boolean {
    val density = LocalDensity.current
    return remember(this) {
        derivedStateOf {
            println("ScrollValue ${this.value}")
            val scrollDp = with(density) { this@rememberIsScrollingDown.value.toDp() }
            scrollDp >= 96.dp
        }
    }.value
}

//@Composable
//private fun LazyListState.isScrollingUp(): Boolean {
//    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
//    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
//    return remember(this) {
//        derivedStateOf {
//            if (previousIndex != firstVisibleItemIndex) {
//                previousIndex > firstVisibleItemIndex
//            } else {
//                previousScrollOffset >= firstVisibleItemScrollOffset
//            }.also {
//                previousIndex = firstVisibleItemIndex
//                previousScrollOffset = firstVisibleItemScrollOffset
//            }
//        }
//    }.value
//}


@Preview(showBackground = true)
@Composable
private fun PokemonDetailsPreview(
    @PreviewParameter(PokemonDetailsPreviewProvider::class) item: PokemonV2Details,
) {
    AppTheme {
        PokemonDetailsView(item = item, onBackPressed = {}, onHeartClicked = {})
    }
}

sealed class HeaderState {
    data object Collapsed : HeaderState()
    data object Expanded : HeaderState()
}

@Composable
fun ScrollState.rememberDebouncedScrollActivityV2(
    debounceMillis: Long = 300,
    threshold: Dp = 32.dp,
): State<HeaderState> {
    val scrollActive = remember { mutableStateOf<HeaderState>(Expanded) }
    val density = LocalDensity.current
    LaunchedEffect(this) {
        snapshotFlow {
            val scrollDp = with(density) { this@rememberDebouncedScrollActivityV2.value.toDp() }
            if (scrollDp >= threshold) Collapsed else Expanded
        }
            .debounce(debounceMillis)
            .distinctUntilChanged()
            .collect { isScrolling ->
                println("DEBUGGG - isScrolling $isScrolling")
                scrollActive.value = isScrolling
            }
    }

    return scrollActive
}

@Composable
fun ScrollState.rememberDebouncedScrollActivity(
    debounceMillis: Long = 300,
    threshold: Dp = 32.dp,
): State<Boolean> {
    val scrollActive = remember { mutableStateOf(false) }
    val density = LocalDensity.current
    LaunchedEffect(this) {
        snapshotFlow {
            val scrollDp = with(density) { this@rememberDebouncedScrollActivity.value.toDp() }
            scrollDp >= threshold
        }
            .debounce(debounceMillis)
            .distinctUntilChanged()
            .collect { isScrolling ->
                println("DEBUGGG - isScrolling $isScrolling")
                scrollActive.value = isScrolling
            }
    }

    return scrollActive
}

//@Preview(showBackground = true)
@Composable
fun ScrollBasedAnimatedHeader() {
    val scrollState = rememberScrollState()
    val shouldCollapse by scrollState.rememberDebouncedScrollActivity() // true se scroll passou do limiar

    val height by animateDpAsState(
        targetValue = if (shouldCollapse) 82.dp else 250.dp,
        animationSpec = tween(300)
    )

    var boxWidthPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val boxWidthDp = with(density) { boxWidthPx.toDp() }

    val cornerRadius by animateDpAsState(
        targetValue = if (shouldCollapse) 0.dp else boxWidthDp / 2,
        animationSpec = tween(300)
    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .onSizeChanged { size ->
                    boxWidthPx = size.width
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = cornerRadius
                    )
                )
                .background(Color(0xFF4CAF50))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            repeat(50) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}