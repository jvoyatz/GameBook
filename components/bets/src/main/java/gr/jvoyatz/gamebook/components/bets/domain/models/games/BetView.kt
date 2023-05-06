package gr.jvoyatz.gamebook.components.bets.domain.models.games

data class BetView(
    val competitionCaption : String,
    val competitions: List<Competition>
)