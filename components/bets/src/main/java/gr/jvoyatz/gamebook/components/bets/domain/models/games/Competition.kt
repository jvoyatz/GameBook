package gr.jvoyatz.gamebook.components.bets.domain.models.games

data class Competition(
    val caption : String,
    val events: List<Event>
)