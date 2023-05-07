package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesEventDto(
    @Json(name = "additionalCaptions")
    val additionalCaptions: UpdatedGamesAdditionalCaptionsDto,
    @Json(name = "betContextId")
    val betContextId: Int,
    @Json(name = "hasBetContextInfo")
    val hasBetContextInfo: Boolean,
    @Json(name = "isHighlighted")
    val isHighlighted: Boolean,
    @Json(name = "liveData")
    val liveData: UpdatedGamesLiveDataDto,
    @Json(name = "markets")
    val markets: List<UpdatedGamesMarketDto>,
    @Json(name = "path")
    val path: String
)