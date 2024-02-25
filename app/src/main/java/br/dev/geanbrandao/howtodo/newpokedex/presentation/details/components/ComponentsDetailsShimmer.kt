package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.common.shimmerEffect
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.DetailsPokeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingFive
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingThree
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo

@Preview(showBackground = true)
@Composable
fun DetailsShimmer() {
    Column(
        modifier = Modifier
            .padding(all = PaddingTwo)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(size = PaddingFive))
        Box(modifier = Modifier
            .size(DetailsPokeSize)
            .shimmerEffect(RoundedCornerShape(percent = 15)))
        Column(
            Modifier
                .padding(top = PaddingThree)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 32.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.size(PaddingOne))
            Box(
                modifier = Modifier
                    .size(width = 50.dp, height = 20.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.size(PaddingTwo))
            Row {
                Box(
                    modifier = Modifier
                        .size(width = 120.dp, height = 35.dp)
                        .shimmerEffect(shape = RoundedCornerShape(percent = 48))
                )
                Spacer(modifier = Modifier.size(PaddingOne))
                Box(
                    modifier = Modifier
                        .size(width = 150.dp, height = 35.dp)
                        .shimmerEffect(shape = RoundedCornerShape(percent = 48))
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(vertical = PaddingThree)
                .fillMaxWidth(),
            color = Black.copy(alpha = 0.05f)
        )
    }
}