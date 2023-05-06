package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BetViewDto(
    @Json(name = "betViewKey")
    val betViewKey: String,
    @Json(name = "competitionContextCaption")
    val competitionContextCaption: String,
    @Json(name = "competitions")
    val competitions: List<CompetitionDto>,
    @Json(name = "marketCaptions")
    val marketCaptions: List<MarketCaptionDto>,
    @Json(name = "modelType")
    val modelType: String,
    @Json(name = "totalCount")
    val totalCount: Int
)