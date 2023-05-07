package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions

import gr.jvoyatz.gamebook.libraries.ui.R


internal object IconUtils {

    fun captionToResourceId(eventId: String?): Int {
        return idsMap[eventId] ?: run {
            R.drawable.circle
        }
    }

    private val idsMap = hashMapOf(
        "Ποδόσφαιρο" to R.drawable.sport_football,
        "Μπάσκετ" to R.drawable.sport_basketball,
        "Τεννις" to R.drawable.sport_tennis
    )
}