package gr.jvoyatz.gamebook.features.gamebook.models.ui

import android.os.Parcelable
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineBetViewUiModel
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

const val ID_HEADER = 0
const val ID_COMPETITION = 1
@Parcelize
sealed class GameBookUiModel: Parcelable{
    abstract val id: Int
    data class Header(val model: List<HeadlineBetViewUiModel>): GameBookUiModel() {
        override val id: Int
            get() = ID_HEADER
    }

    data class Competition(val competition: String, val uiModels: List<CompetitionUiModel>): GameBookUiModel() {
        @IgnoredOnParcel
        var expanded: Boolean = true
        override val id: Int
            get() = ID_COMPETITION
    }
}
