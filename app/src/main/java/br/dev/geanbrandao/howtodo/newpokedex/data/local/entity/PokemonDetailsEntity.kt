package br.dev.geanbrandao.howtodo.newpokedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_details")
data class PokemonDetailsEntity(
    @PrimaryKey val id: Int,
    val genderRate: Int,
    val evolutions: String, // json list convertido para string [1, 2, 3]
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: String,
)
