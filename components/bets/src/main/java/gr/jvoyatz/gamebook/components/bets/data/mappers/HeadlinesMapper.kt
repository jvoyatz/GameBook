package gr.jvoyatz.gamebook.components.bets.data.mappers

import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.Headline
import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.HeadlineBetView
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.HeadlineBetViewDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.HeadlineDto

internal object HeadlinesMapper {

    fun List<HeadlineDto>.toHeadlines() = this.map { it.toHeadline() }
    fun HeadlineDto.toHeadline() = Headline(
        betViews = this.betViews.map { it.toHeadlineBetView() }
    )

    fun HeadlineBetViewDto.toHeadlineBetView() = HeadlineBetView(
        competitor1 = this.competitor1Caption,
        competitor2 = this.competitor2Caption,
        startTime = this.startTime,
    )
}