package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesEvent(
    @Json(name = "additionalCaptions")
    val additionalCaptions: GamesAdditionalCaptions,
    @Json(name = "betContextId")
    val betContextId: Int,
    @Json(name = "hasBetContextInfo")
    val hasBetContextInfo: Boolean,
    @Json(name = "isHighlighted")
    val isHighlighted: Boolean,
    @Json(name = "liveData")
    val liveData: GamesLiveData,
    @Json(name = "markets")
    val markets: List<GamesMarket>,
    @Json(name = "path")
    val path: String
)