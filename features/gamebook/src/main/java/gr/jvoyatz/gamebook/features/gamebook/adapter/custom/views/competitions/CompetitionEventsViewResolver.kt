package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions

import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel
import gr.jvoyatz.gamebook.libraries.shared.Constants

class CompetitionEventsViewResolver(
    private val view: CompetitionEventViewInterface,
    private val competitionTitle: String,
    private val competitions: List<CompetitionUiModel>,
    isExpanded: Boolean,
    onExpandClicked: () -> Unit
) {
    init {
        view.setOnExpandClicked(onExpandClicked)
        setTitle()
        setEventsList()
        setIcon()
        setCollapsedOrExpanded(isExpanded)
    }

    private fun setIcon() {
        view.setIcon(IconUtils.captionToResourceId(competitionTitle))
    }

    private fun setTitle() {
        with(competitionTitle) {
            if (isNotEmpty()) {
                view.setTitle(this)
            } else {
                view.setTitle(Constants.DOTS)
            }
        }
    }

    private fun setEventsList() {
        with(competitions) {
            if (!this.isNullOrEmpty()) {
                view.setEvents(competitions)
            } else {
                view.showNoEvents()
            }
        }
    }
    private fun setCollapsedOrExpanded(isExpanded: Boolean) {
        if (isExpanded)
            view.expand()
        else
            view.collapse()
    }
}