package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.dev.geanbrandao.howtodo.newpokedex.common.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.PokemonPreviewProvider
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeSmallSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne

@Composable
fun PokemonBasicInfo(
    item: PokemonV2,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = item.numberFormatted,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            text = item.name.capitalize(),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(size = PaddingOne))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PokemonType(
                type = item.typeOne,
                style = MaterialTheme.typography.bodyMedium,
                iconSize = IconTypeSmallSize,
            )
            item.typeTwo?.let {
            Spacer(modifier = Modifier.size(PaddingOne))
                PokemonType(
                    type = it,
                    style = MaterialTheme.typography.bodyMedium,
                    iconSize = IconTypeSmallSize,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonBasicInfoPreview(
    @PreviewParameter(PokemonPreviewProvider::class) item: PokemonV2,
) {
    AppTheme {
        PokemonBasicInfo(item)
    }
}
