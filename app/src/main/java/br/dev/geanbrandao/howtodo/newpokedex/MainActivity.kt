package br.dev.geanbrandao.howtodo.newpokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.dev.geanbrandao.howtodo.newpokedex.navigation.NavigationGraph
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.NewPokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewPokedexTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        NavigationGraph(modifier = Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}