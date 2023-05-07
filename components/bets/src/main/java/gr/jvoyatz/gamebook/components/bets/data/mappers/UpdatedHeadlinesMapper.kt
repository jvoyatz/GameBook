package gr.jvoyatz.gamebook.components.bets.data.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.Headline
import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.HeadlineBetView
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated.UpdatedHeadlineBetViewDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated.UpdatedHeadlineHeadlineItemDto
import gr.jvoyatz.gamebook.libraries.shared.Constants

object UpdatedHeadlinesMapper {
    fun List<UpdatedHeadlineHeadlineItemDto>.toHeadlines() = this.map { it.toHeadline() }
    fun UpdatedHeadlineHeadlineItemDto.toHeadline() = Headline(
        betViews = this.betViews.mapNotNull { it.toHeadlineBetView() }.distinctBy { it.id }
    )

    fun UpdatedHeadlineBetViewDto.toHeadlineBetView(): HeadlineBetView? {
        return this.betContextId?.let {
            HeadlineBetView(
                id = this.betContextId!!,
                competitor1 = this.competitor1Caption ?: Constants.DASH_WITH_SPACE,
                competitor2 = this.competitor2Caption ?: Constants.DASH_WITH_SPACE,
                startTime = this.startTime ?: Constants.DASH_WITH_SPACE,
            )
        }
    }
}