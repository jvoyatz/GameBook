package gr.jvoyatz.gamebook.features.gamebook.models.ui.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


const val ID_COMPETITION_HEADER = 1
const val ID_COMPETITION_EVENTS = 2

@Parcelize
sealed class CompetitionUiModel: Parcelable {
    abstract val uiId: Int
    data class CompetitionUiHeader(
        val header: String
    ): CompetitionUiModel() {
        override val uiId: Int
            get() = ID_COMPETITION_HEADER
    }

    data class CompetitionUiEvent(
        val id: Int,
        val event: EventUiModel
    ): CompetitionUiModel() {
        override val uiId: Int
            get() = ID_COMPETITION_EVENTS
    }
    data class CompetitionUiEvents(
        val id: Int,
        val events: List<EventUiModel>
    ): CompetitionUiModel() {
        override val uiId: Int
            get() = ID_COMPETITION_EVENTS
    }
}
