package gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedHeadlineBetViewDto(
    @Json(name = "betContextId")
    val betContextId: Long?,
    @Json(name = "betItems")
    val betItems: List<UpdatedHeadlineBetItemDto>?,
    @Json(name = "betViewKey")
    val betViewKey: String,
    @Json(name = "competitor1Caption")
    val competitor1Caption: String?,
    @Json(name = "competitor2Caption")
    val competitor2Caption: String?,
    @Json(name = "displayFormat")
    val displayFormat: String,
    @Json(name = "imageId")
    val imageId: Int,
    @Json(name = "liveData")
    val liveData: UpdatedHeadlineLiveDataDto?,
    @Json(name = "marketTags")
    val marketTags: List<String> = listOf(),
    @Json(name = "marketViewGroupId")
    val marketViewGroupId: Int?,
    @Json(name = "marketViewId")
    val marketViewId: Int?,
    @Json(name = "modelType")
    val modelType: String,
    @Json(name = "path")
    val path: String?,
    @Json(name = "rootMarketViewGroupId")
    val rootMarketViewGroupId: Int?,
    @Json(name = "startTime")
    val startTime: String?,
    @Json(name = "text")
    val text: String,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
)