package gr.jvoyatz.gamebook.features.gamebook.models.ui.games

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventUiModel(
    val id: Int,
    val competitor1: String,
    val competitor2: String,
    val elapsed: String,
): Parcelable