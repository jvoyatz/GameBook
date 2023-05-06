package gr.jvoyatz.gamebook.libraries.network.dto.headlines


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeadlinesBetView(
    @Json(name = "betContextId")
    val betContextId: Int,
    @Json(name = "betItems")
    val betItems: List<HeadlinesBetItemDto>,
    @Json(name = "betViewKey")
    val betViewKey: String,
    @Json(name = "competitor1Caption")
    val competitor1Caption: String,
    @Json(name = "competitor2Caption")
    val competitor2Caption: String,
    @Json(name = "displayFormat")
    val displayFormat: String,
    @Json(name = "imageId")
    val imageId: Int,
    @Json(name = "liveData")
    val liveData: HeadlinesLiveDataDto,
    @Json(name = "marketTags")
    val marketTags: List<Any>,
    @Json(name = "marketViewGroupId")
    val marketViewGroupId: Int,
    @Json(name = "marketViewId")
    val marketViewId: Int,
    @Json(name = "modelType")
    val modelType: String,
    @Json(name = "path")
    val path: String,
    @Json(name = "rootMarketViewGroupId")
    val rootMarketViewGroupId: Int,
    @Json(name = "startTime")
    val startTime: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "url")
    val url: Any?
)