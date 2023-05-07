package gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeadlineBetViewUiModel(
    val id: Long,
    val competitor1: String,
    val competitor2: String,
    val startTime: String,
): Parcelable