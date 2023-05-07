package gr.jvoyatz.gamebook.features.gamebook.models.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.games.BetView
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Event
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Game
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.BetViewUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.EventUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.GameUiModel

internal object GameUiModelMapper {
    fun List<Game>.toUiModels() = this.map { it.toGameUiModel() }

    private fun Game.toGameUiModel() = GameUiModel(
        betViews = this.betViews.map { it.toBetViewUiModel() }
    )
    private fun BetView.toBetViewUiModel()= BetViewUiModel(
        competitionCaption = this.competitionCaption,
        competitions = this.competitions.flatMap {
             val events = it.events.map { it.toEventUiModel() }
                 .map { CompetitionUiModel.CompetitionUiEvent(it.id, it) }
            listOf(CompetitionUiModel.CompetitionUiHeader(it.caption)) + events
        }
    )
    private fun Event.toEventUiModel() = EventUiModel(
        id = this.id,
        competitor1 = this.competitor1,
        competitor2 = this.competitor2,
        elapsed = this.elapsed,
    )
}