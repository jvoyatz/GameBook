package gr.jvoyatz.gamebook.components.bets.domain.models

import gr.jvoyatz.gamebook.components.bets.domain.models.games.Game
import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.Headline

data class EventsDataContainer(
    val headlines: List<Headline>,
    val games: List<Game>
){

    fun isEmpty() = headlines.isEmpty() && games.isEmpty()
    companion object {
        val EMPTY = EventsDataContainer(listOf(), listOf())
    }
}