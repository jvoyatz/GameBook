package gr.jvoyatz.gamebook.components.bets.data.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.games.BetView
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Competition
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Event
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Game
import gr.jvoyatz.gamebook.libraries.network.dto.games.BetViewDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.CompetitionDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.EventDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.GameDto

internal object GamesMapper {

    fun List<GameDto>.toGames() = this.map { it.toGames() }

    fun GameDto.toGames() = Game(
        betViews = this.betViews.map { it.toGameBetView() }
    )
    private fun BetViewDto.toGameBetView()= BetView(
        competitionCaption = this.competitionContextCaption,
        competitions = this.competitions.map { it.toCompetition() }
    )

    private fun CompetitionDto.toCompetition() = Competition(
        id = this.betContextId,
        caption = "${this.caption}${gr.jvoyatz.gamebook.libraries.shared.Constants.DASH_WITH_SPACE}${this.regionCaption}",
        events = this.events.map { it.toEvent() }
    )
    private fun EventDto.toEvent() = Event(
        id = this.betContextId,
        competitor1 = this.additionalCaptions.competitor1,
        competitor2 = this.additionalCaptions.competitor2,
        elapsed = this.liveData.elapsed,
    )
}