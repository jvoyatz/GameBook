package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    @Json(name = "additionalCaptions")
    val additionalCaptions: AdditionalCaptionsDto,
    @Json(name = "betContextId")
    val betContextId: Int,
    @Json(name = "hasBetContextInfo")
    val hasBetContextInfo: Boolean,
    @Json(name = "isHighlighted")
    val isHighlighted: Boolean,
    @Json(name = "liveData")
    val liveData: LiveDataDto,
    @Json(name = "markets")
    val markets: List<Market>,
    @Json(name = "path")
    val path: String
)