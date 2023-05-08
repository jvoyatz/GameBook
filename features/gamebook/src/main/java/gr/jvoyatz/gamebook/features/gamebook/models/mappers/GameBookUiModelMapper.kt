package gr.jvoyatz.gamebook.features.gamebook.models.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.features.gamebook.models.mappers.GameUiModelMapper.toUiModels
import gr.jvoyatz.gamebook.features.gamebook.models.mappers.HeadlinesUiModelMapper.toUiModels
import gr.jvoyatz.gamebook.features.gamebook.models.ui.GameBookUiModel

internal object GameBookUiModelMapper {
    fun EventsDataContainer.toUiModels(): List<GameBookUiModel> {

        val headers = GameBookUiModel.Header(headlines.toUiModels().flatMap { it.betViews })

        val competitions = games.toUiModels().toMutableList().flatMap { it.betViews }
            .map { GameBookUiModel.Competition(it.competitionCaption, it.competitions) }

        return listOf<GameBookUiModel>(headers) + competitions
    }
}