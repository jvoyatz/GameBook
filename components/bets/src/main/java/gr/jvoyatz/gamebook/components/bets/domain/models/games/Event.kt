package gr.jvoyatz.gamebook.components.bets.domain.models.games

data class Event(
    val id: Int,
    val competitor1: String,
    val competitor2: String,
    val elapsed: String,
)