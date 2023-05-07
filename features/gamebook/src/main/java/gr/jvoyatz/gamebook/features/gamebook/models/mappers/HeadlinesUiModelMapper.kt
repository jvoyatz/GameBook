package gr.jvoyatz.gamebook.features.gamebook.models.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.Headline
import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.HeadlineBetView
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineBetViewUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineUiModel

object HeadlinesUiModelMapper {
    fun List<Headline>.toUiModels() = this.map { it.toHeadlineUiModel() }
    fun Headline.toHeadlineUiModel() = HeadlineUiModel(
        betViews = this.betViews.map { it.toHeadlineBetViewUiModel() }
    )
    fun HeadlineBetView.toHeadlineBetViewUiModel() = HeadlineBetViewUiModel(
        id = this.id,
        competitor1 = this.competitor1,
        competitor2 = this.competitor2,
        startTime = this.startTime,
    )
}