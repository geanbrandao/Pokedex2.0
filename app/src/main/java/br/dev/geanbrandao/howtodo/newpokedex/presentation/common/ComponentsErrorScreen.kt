package br.dev.geanbrandao.howtodo.newpokedex.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.ErrorColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingFive
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingThree
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelLarge

@Preview(showBackground = true)
@Composable
fun ErrorScreen(
    onTryAgain: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(PaddingThree),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "Icone de erro",
            Modifier
                .padding(top = PaddingThree)
                .size(90.dp),
            tint = ErrorColor,
        )
        Text(
            text = stringResource(R.string.default_error_message),
            textAlign = TextAlign.Center,
            fontSize = TextLabelLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = PaddingFive)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onTryAgain,
            Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.button_try_again))
        }

    }
}