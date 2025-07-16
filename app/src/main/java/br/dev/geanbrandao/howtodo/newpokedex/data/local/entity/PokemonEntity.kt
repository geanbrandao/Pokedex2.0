package br.dev.geanbrandao.howtodo.newpokedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val typeOne: String,
    val typeTwo: String? = null,
    val height: Int,
    val weight: Int,
    val stats: String, // json list convertido para string [{"name": "hp", "value": 45}]
    val abilities: String, // json list convertido para string [{"name": "overgrow"}]
    val isFavorite: Boolean = false,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: String,
)

//@Entity(tableName = "pokemon",)
//data class PokemonEntity(
//    @PrimaryKey val id: Int,
//    val name: String,
//    val height: Float,
//    val weight: Float,
//    val generation: String,
//    val genderRate: Int,
//    val imageUrl: String,
//    val imageUrlShiny: String,
//    val typeOne: String,
//    val typeTwo: String?,
//    val stats: String, // json list convertido para string [{"name": "hp", "value": 45}]
//    val abilities: String, // json list convertido para string [{"name": "overgrow"}]
//    val evolutionChainUrl: String, // json list convertido para string [{"name": "overgrow"}]
//
//)