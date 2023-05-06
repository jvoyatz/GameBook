package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesBetDto(
    @Json(name = "betViewKey")
    val betViewKey: String,
    @Json(name = "competitionContextCaption")
    val competitionContextCaption: String,
    @Json(name = "competitions")
    val competitions: List<GamesCompetitionDto>,
    @Json(name = "marketCaptions")
    val marketCaptions: List<GamesMarketCaptionDto>,
    @Json(name = "modelType")
    val modelType: String,
    @Json(name = "totalCount")
    val totalCount: Int
)