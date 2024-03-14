package br.dev.geanbrandao.howtodo.newpokedex.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name:String,
    val height: Int,
    val weight: Int,
    val typeOne: String,
    val typeTwo: String?,
    val abilities: List<String>,
    val stats: List<Stat>,
)

data class Stat(
    val value: Int,
    val name: String,
)