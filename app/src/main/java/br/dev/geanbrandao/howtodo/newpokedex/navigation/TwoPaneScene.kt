package br.dev.geanbrandao.howtodo.newpokedex.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy
import androidx.window.core.layout.WindowWidthSizeClass

class TwoPaneScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>,
) : Scene<T> {

    override val entries: List<NavEntry<T>> = listOf(firstEntry, secondEntry)

    override val content: @Composable (() -> Unit)
        get() = {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.weight(0.45f)
                ) {
                    firstEntry.Content()
                }
                Column(
                    modifier = Modifier.weight(0.55f)
                ) {
                    secondEntry.Content()
                }
            }
        }

    companion object {
        internal const val TWO_PANE_SCENE_KEY = "TwoPaneScene"

        fun twoPane() = mapOf(TWO_PANE_SCENE_KEY to true)
    }
}

class TwoPaneSceneStrategy<T : Any> : SceneStrategy<T> {

    @Composable
    override fun calculateScene(entries: List<NavEntry<T>>, onBack: (Int) -> Unit): Scene<T>? {

        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
            return null
        }

        val lastTwoEntries = entries.takeLast(2)

        return if (lastTwoEntries.size == 2 &&
            lastTwoEntries.all { it.metadata.containsKey(TwoPaneScene.TWO_PANE_SCENE_KEY) }
            ) {

            val firstEntry = lastTwoEntries.first()
            val secondaryEntry = lastTwoEntries.last()

            val sceneKey = Pair(firstEntry.contentKey, secondaryEntry.contentKey)

            TwoPaneScene(
                key = sceneKey,
                previousEntries = entries.dropLast(1),
                firstEntry = firstEntry,
                secondEntry = secondaryEntry,
            )
        } else {
            null
        }
    }
}