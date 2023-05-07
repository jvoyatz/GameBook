package gr.jvoyatz.gamebook.features.gamebook.models.ui.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameUiModel(
    val betViews: List<BetViewUiModel>
): Parcelable