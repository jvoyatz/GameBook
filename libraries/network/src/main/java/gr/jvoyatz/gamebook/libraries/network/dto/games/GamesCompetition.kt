package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesCompetition(
    @Json(name = "betContextId")
    val betContextId: Int,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "events")
    val events: List<GamesEvent>,
    @Json(name = "regionCaption")
    val regionCaption: String
)