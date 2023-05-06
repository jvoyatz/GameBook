package gr.jvoyatz.gamebook.libraries.network.dto.headlines


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeadlineDto(
    @Json(name = "betViews")
    val betViews: List<HeadlineBetViewDto>,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "marketViewKey")
    val marketViewKey: String,
    @Json(name = "marketViewType")
    val marketViewType: String,
    @Json(name = "modelType")
    val modelType: String
)