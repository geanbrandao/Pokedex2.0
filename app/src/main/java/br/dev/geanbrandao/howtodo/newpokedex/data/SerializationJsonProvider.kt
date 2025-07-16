package br.dev.geanbrandao.howtodo.newpokedex.data

import kotlinx.serialization.json.Json
import org.koin.core.annotation.Factory

@Factory
class SerializationJsonProvider(
    val json: Json,
) {
    inline fun <reified T> fromJson(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }

    inline fun <reified T> toJson(value: T): String {
        return json.encodeToString(value)
    }
}