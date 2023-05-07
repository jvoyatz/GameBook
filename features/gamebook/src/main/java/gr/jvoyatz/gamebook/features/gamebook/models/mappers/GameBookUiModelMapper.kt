package gr.jvoyatz.gamebook.features.gamebook.models.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.features.gamebook.models.mappers.GameUiModelMapper.toUiModels
import gr.jvoyatz.gamebook.features.gamebook.models.mappers.HeadlinesUiModelMapper.toUiModels
import gr.jvoyatz.gamebook.features.gamebook.models.ui.GameBookUiModel
import timber.log.Timber

internal object GameBookUiModelMapper {
    fun EventsDataContainer.toUiModels(): List<GameBookUiModel> {

        games.toUiModels().forEach {
            Timber.tag("UI_MODEL").d("game ui models size ${it.betViews.size}")
            it.betViews.forEach {
                Timber.tag("UI_MODEL").d("${it.competitionCaption} and size =  ${it.competitions.size}")
            }
        }

        val toUiModels = games.toUiModels()
        val betViews = toUiModels[0].betViews
        val betview = betViews[0].copy(competitionCaption = "TENNIS")
        val list = games.toUiModels().toMutableList()

        //list.add(games.toUiModels()[0].copy(betViews = listOf(betview)))

        val headlinesBetViewUiModels = headlines.toUiModels().flatMap { it.betViews }

        val headers = GameBookUiModel.Header(headlinesBetViewUiModels)
        val competitions = list.flatMap { it.betViews }
            .map { GameBookUiModel.Competition(it.competitionCaption, it.competitions) }

        //GameBookUiModel.Header(headlines.toUiModels())
        return listOf<GameBookUiModel>(headers) + competitions
    }
}