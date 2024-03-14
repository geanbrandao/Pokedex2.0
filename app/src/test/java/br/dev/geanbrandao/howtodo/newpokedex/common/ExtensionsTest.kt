package br.dev.geanbrandao.howtodo.newpokedex.common

import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import org.junit.Assert.assertEquals
import org.junit.Test


class ExtensionsTest {

    @Test
    fun `given string type then return type model`() {
        val grass = "grass"

        val result = grass.getTypeModel()

        assertEquals(result, PokemonTypeModel.Grass)
    }

    @Test
    fun `given Int color return `() {

    }
}