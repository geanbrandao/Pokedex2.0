package br.dev.geanbrandao.howtodo.newpokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.dev.geanbrandao.howtodo.newpokedex.data.local.dao.PokemonDao
import br.dev.geanbrandao.howtodo.newpokedex.data.local.entity.PokemonDetailsEntity
import br.dev.geanbrandao.howtodo.newpokedex.data.local.entity.PokemonEntity

const val DB_VERSION = 1
const val DB_NAME = "newPokedex.db"
@Database(
    entities = [PokemonEntity::class, PokemonDetailsEntity::class],
    exportSchema = true,
    version = DB_VERSION,
)
abstract class AppDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao
}