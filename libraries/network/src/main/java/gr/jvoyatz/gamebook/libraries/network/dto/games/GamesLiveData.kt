package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesLiveData(
    @Json(name = "adjustTimeMillis")
    val adjustTimeMillis: Int,
    @Json(name = "awayCorners")
    val awayCorners: Int,
    @Json(name = "awayGoals")
    val awayGoals: Int,
    @Json(name = "awayPenaltyKicks")
    val awayPenaltyKicks: Int,
    @Json(name = "awayRedCards")
    val awayRedCards: Int,
    @Json(name = "awayYellowCards")
    val awayYellowCards: Int,
    @Json(name = "duration")
    val duration: Any?,
    @Json(name = "durationSeconds")
    val durationSeconds: Any?,
    @Json(name = "elapsed")
    val elapsed: String,
    @Json(name = "elapsedSeconds")
    val elapsedSeconds: Double,
    @Json(name = "homeCorners")
    val homeCorners: Int,
    @Json(name = "homeGoals")
    val homeGoals: Int,
    @Json(name = "homePenaltyKicks")
    val homePenaltyKicks: Int,
    @Json(name = "homeRedCards")
    val homeRedCards: Int,
    @Json(name = "homeYellowCards")
    val homeYellowCards: Int,
    @Json(name = "isInPlay")
    val isInPlay: Boolean,
    @Json(name = "isInPlayPaused")
    val isInPlayPaused: Boolean,
    @Json(name = "isInterrupted")
    val isInterrupted: Boolean,
    @Json(name = "isLive")
    val isLive: Boolean,
    @Json(name = "liveStreamingCountries")
    val liveStreamingCountries: String?,
    @Json(name = "phaseCaption")
    val phaseCaption: String,
    @Json(name = "phaseCaptionLong")
    val phaseCaptionLong: String,
    @Json(name = "phaseSysname")
    val phaseSysname: String,
    @Json(name = "referenceTime")
    val referenceTime: String,
    @Json(name = "referenceTimeUnix")
    val referenceTimeUnix: Int,
    @Json(name = "sportradarMatchId")
    val sportradarMatchId: Int?,
    @Json(name = "supportsAchievements")
    val supportsAchievements: Boolean,
    @Json(name = "supportsActions")
    val supportsActions: Boolean,
    @Json(name = "timeToNextPhase")
    val timeToNextPhase: String?,
    @Json(name = "timeToNextPhaseSeconds")
    val timeToNextPhaseSeconds: Double?,
    @Json(name = "timeline")
    val timeline: Any?
)