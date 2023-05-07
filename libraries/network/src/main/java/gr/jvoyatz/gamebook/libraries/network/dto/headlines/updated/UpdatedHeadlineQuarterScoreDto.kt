package gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedHeadlineQuarterScoreDto(
    @Json(name = "awayScore")
    val awayScore: Int,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "homeScore")
    val homeScore: Int
)