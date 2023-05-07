package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesBetViewDto(
    @Json(name = "betViewKey")
    val betViewKey: String,
    @Json(name = "competitionContextCaption")
    val competitionContextCaption: String,
    @Json(name = "competitions")
    val competitions: List<UpdatedGamesCompetitionDto>,
    @Json(name = "marketCaptions")
    val marketCaptions: List<UpdatedGamesMarketCaptionDto>,
    @Json(name = "modelType")
    val modelType: String,
    @Json(name = "totalCount")
    val totalCount: Int
)