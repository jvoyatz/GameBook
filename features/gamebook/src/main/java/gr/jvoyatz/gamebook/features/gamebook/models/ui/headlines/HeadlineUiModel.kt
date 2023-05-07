package gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeadlineUiModel(
    val betViews: List<HeadlineBetViewUiModel>
): Parcelable