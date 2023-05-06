package gr.jvoyatz.gamebook.libraries.network.dto.headlines


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeadlineQuarterScoreDto(
    @Json(name = "awayScore")
    val awayScore: Int,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "homeScore")
    val homeScore: Int
)