package ies.luiscarrillodesotomayor.sw_jorgelb.Personajes

data class PersonajesJson(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)