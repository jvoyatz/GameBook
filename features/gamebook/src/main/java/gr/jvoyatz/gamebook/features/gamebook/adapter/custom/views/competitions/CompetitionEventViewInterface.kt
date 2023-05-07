package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions

import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel

interface CompetitionEventViewInterface {
    fun setTitle(title: String)

    fun setIcon(resId: Int)
    fun setEvents(events: List<CompetitionUiModel>)

    fun showNoEvents()

    fun setOnExpandClicked(block: () -> Unit)

    fun collapse()

    fun expand()
}