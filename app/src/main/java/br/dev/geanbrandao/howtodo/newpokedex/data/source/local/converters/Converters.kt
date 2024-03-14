package br.dev.geanbrandao.howtodo.newpokedex.data.source.local.converters

import androidx.room.TypeConverter
import br.dev.geanbrandao.howtodo.newpokedex.data.source.local.entities.Stat

class Converters {

    @TypeConverter
    fun fromStatToString(stat: Stat): String {
        return "${stat.name}:${stat.value}"
    }

    @TypeConverter
    fun fromStringToStat(stat: String): Stat {
        val split = stat.split(":")
        return Stat(
            name = split[0],
            value = split[1].toInt()
        )
    }
}