package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.common.shimmerEffect
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo

@Preview
@Composable
fun PokemonShimmerItem() {
    Row(
        modifier = Modifier
            .padding(bottom = PaddingTwo)
            .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(percent = 15))
            .fillMaxWidth()
            .height(125.dp)
            .padding(start = PaddingTwo),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            Modifier.padding(vertical = PaddingTwo).weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 48.dp, height = 16.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.size(PaddingHalf))
            Box(
                modifier = Modifier
                    .size(width = 120.dp, height = 28.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.size(PaddingOne))
            Row {
                Box(
                    modifier = Modifier
                        .size(width = 90.dp, height = 24.dp)
                        .shimmerEffect(shape = RoundedCornerShape(percent = 48))
                )
                Spacer(modifier = Modifier.size(PaddingHalf))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 24.dp)
                        .shimmerEffect(shape = RoundedCornerShape(percent = 48))
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = 125.dp)
                .shimmerEffect(RoundedCornerShape(percent = 15))
        )
    }
}