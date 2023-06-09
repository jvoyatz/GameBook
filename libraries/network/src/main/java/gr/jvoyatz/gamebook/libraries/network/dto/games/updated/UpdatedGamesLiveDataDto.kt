package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesLiveDataDto(
    @Json(name = "adjustTimeMillis")
    val adjustTimeMillis: Int,
    @Json(name = "awayPoints")
    val awayPoints: Int,
    @Json(name = "duration")
    val duration: String?,
    @Json(name = "durationSeconds")
    val durationSeconds: Int?,
    @Json(name = "elapsed")
    val elapsed: String,
    @Json(name = "elapsedSeconds")
    val elapsedSeconds: Double,
    @Json(name = "homePoints")
    val homePoints: Int,
    @Json(name = "homePossession")
    val homePossession: Boolean?,
    @Json(name = "isInPlay")
    val isInPlay: Boolean,
    @Json(name = "isInPlayPaused")
    val isInPlayPaused: Boolean,
    @Json(name = "isInterrupted")
    val isInterrupted: Boolean,
    @Json(name = "isLive")
    val isLive: Boolean,
    @Json(name = "liveStreamingCountries")
    val liveStreamingCountries: String,
    @Json(name = "phaseCaption")
    val phaseCaption: String,
    @Json(name = "phaseCaptionLong")
    val phaseCaptionLong: String,
    @Json(name = "phaseSysname")
    val phaseSysname: String,
    @Json(name = "quarterScores")
    val quarterScores: Any? = null,
    @Json(name = "referenceTime")
    val referenceTime: String,
    @Json(name = "referenceTimeUnix")
    val referenceTimeUnix: Int,
    @Json(name = "remaining")
    val remaining: String,
    @Json(name = "remainingSeconds")
    val remainingSeconds: Double,
    @Json(name = "sportradarMatchId")
    val sportradarMatchId: Int,
    @Json(name = "supportsAchievements")
    val supportsAchievements: Boolean,
    @Json(name = "supportsActions")
    val supportsActions: Boolean,
    @Json(name = "timeToNextPhase")
    val timeToNextPhase: String?,
    @Json(name = "timeToNextPhaseSeconds")
    val timeToNextPhaseSeconds: Double?,
    @Json(name = "timeline")
    val timeline: Any? = null
)