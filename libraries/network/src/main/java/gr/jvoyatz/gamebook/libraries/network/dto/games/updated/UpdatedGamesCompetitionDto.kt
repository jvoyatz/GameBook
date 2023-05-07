package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesCompetitionDto(
    @Json(name = "betContextId")
    val betContextId: Int,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "events")
    val events: List<UpdatedGamesEventDto>,
    @Json(name = "regionCaption")
    val regionCaption: String
)