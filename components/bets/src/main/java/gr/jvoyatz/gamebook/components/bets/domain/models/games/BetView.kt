package gr.jvoyatz.gamebook.components.bets.domain.models.games

data class BetView(
    val competitionCaption : String,
    var competitions: List<Competition>
)