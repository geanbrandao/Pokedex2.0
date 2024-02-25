package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

enum class PokemonTypeEnum(val identifier: String) {
    WATER("water"),
    GRASS("grass"),
    DRAGON("dragon"),
    ELECTRIC("electric"),
    FAIRY("fairy"),
    GHOST("ghost"),
    FIRE("fire"),
    ICE("ice"),
    BUG("bug"),
    FIGHTING("fighting"),
    NORMAL("normal"),
    DARK("dark"),
    STEEL("steel"),
    ROCK("rock"),
    PSYCHIC("psychic"),
    GROUND("ground"),
    POISON("poison"),
    FLYING("flying");

    companion object {
        fun from(identifier: String) = entries.find { it.identifier == identifier }
    }
}