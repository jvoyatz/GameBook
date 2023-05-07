package gr.jvoyatz.gamebook.components.bets.data.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.games.BetView
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Competition
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Event
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Game
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesBetViewDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesCompetitionDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesEventDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesItemDto
import gr.jvoyatz.gamebook.libraries.shared.Constants

object UpdatedGamesMapper {
    fun List<UpdatedGamesItemDto>.toGames() = this.map { it.toGames() }

    fun UpdatedGamesItemDto.toGames() = Game(
        betViews = this.betViews.map { it.toGameBetView() }
    )
    private fun UpdatedGamesBetViewDto.toGameBetView()= BetView(
        competitionCaption = this.competitionContextCaption,
        competitions = this.competitions.map { it.toCompetition() }
    )

    private fun UpdatedGamesCompetitionDto.toCompetition() = Competition(
        id = this.betContextId,
        caption = "${this.caption}${Constants.DASH_WITH_SPACE}${this.regionCaption}",
        events = this.events.map { it.toEvent() }
    )
    private fun UpdatedGamesEventDto.toEvent() = Event(
        id = this.betContextId,
        competitor1 = this.additionalCaptions.competitor1,
        competitor2 = this.additionalCaptions.competitor2,
        elapsed = this.liveData.elapsed,
    )
}