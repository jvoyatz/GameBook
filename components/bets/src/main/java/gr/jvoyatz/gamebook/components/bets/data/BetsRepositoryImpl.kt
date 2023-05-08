package gr.jvoyatz.gamebook.components.bets.data

import gr.jvoyatz.gamebook.components.bets.data.mappers.GamesMapper.toGames
import gr.jvoyatz.gamebook.components.bets.data.mappers.HeadlinesMapper.toHeadlines
import gr.jvoyatz.gamebook.components.bets.data.mappers.UpdatedGamesMapper.toGames
import gr.jvoyatz.gamebook.components.bets.data.mappers.UpdatedHeadlinesMapper.toHeadlines
import gr.jvoyatz.gamebook.components.bets.data.sources.BetsRemoteDataSource
import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.components.bets.domain.models.GameBookException
import gr.jvoyatz.gamebook.components.bets.domain.models.games.BetView
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Competition
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Event
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Game
import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.Headline
import gr.jvoyatz.gamebook.components.bets.domain.repositories.BetsRepository
import gr.jvoyatz.gamebook.libraries.network.GameBookApi
import gr.jvoyatz.gamebook.libraries.network.TOKEN
import gr.jvoyatz.gamebook.libraries.network.dto.games.GameDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesItemDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.HeadlineDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated.UpdatedHeadlineHeadlineItemDto
import gr.jvoyatz.gamebook.libraries.network.response.extractError
import gr.jvoyatz.gamebook.libraries.network.response.invoke
import gr.jvoyatz.gamebook.libraries.network.response.isError
import gr.jvoyatz.gamebook.libraries.network.response.isSuccess
import gr.jvoyatz.gamebook.libraries.network.response.onSuspendedSuccess
import gr.jvoyatz.gamebook.libraries.shared.resultdata.ResultData
import gr.jvoyatz.gamebook.libraries.shared.resultdata.suspendedResultOf
import timber.log.Timber

/**
 * Implementation for [BetsRepository]
 *
 * Contains a create method to create a new instance
 */
internal class BetsRepositoryImpl
internal constructor(
    private val datasource: BetsRemoteDataSource
) : BetsRepository {

    private lateinit var cachedData: EventsDataContainer

    override suspend fun getToken(): ResultData<Unit> {
        return suspendedResultOf {
            if(TOKEN.isBlank()) {
                datasource.getToken()
                    .onSuspendedSuccess {
                        TOKEN = this.accessToken
                    }.also {
                        if(it.isError()){
                            throw GameBookException(it.extractError()?: "unknown error during authentication")
                        }
                    }
            }else{
                Timber.d("TOKEN PROVIDED THROUGH LOCAL.PROPERTIES $TOKEN")
            }
        }
    }

    override suspend fun getData(): ResultData<EventsDataContainer> {
        return suspendedResultOf {
            val headlines = getInitHeadlines()
            val games = getGames()
            EventsDataContainer(headlines.toHeadlines(), games.toGames()).also {
                cachedData = it
            }
        }
    }



    override suspend fun getUpdatedData(): ResultData<EventsDataContainer> {
        return suspendedResultOf {
            val headlines = getUpdatedHeadlines()
            val games = getUpdatedGames()

            getMergedWithCacheEventData(headlines.toHeadlines(), games.toGames()).let {
                EventsDataContainer(it.first, it.second)
            }
        }
    }

    private suspend fun getInitHeadlines(): List<HeadlineDto> {
        val response = datasource.getHeadlines()

        return if (response.isSuccess()) {
            response() ?: throw GameBookException("headlines are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private suspend fun getUpdatedHeadlines(): List<UpdatedHeadlineHeadlineItemDto> {
        val response = datasource.getUpdatedHeadlines()
        return if (response.isSuccess()) {
            response() ?: throw GameBookException("headlines are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private suspend fun getGames(): List<GameDto> {
        val response = datasource.getGames()

        return if (response.isSuccess()) {
            response() ?: throw GameBookException("games are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private suspend fun getUpdatedGames(): List<UpdatedGamesItemDto> {
        val response = datasource.getUpdatedGames()

        return if (response.isSuccess()) {
            response() ?: throw GameBookException("games are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    /**
     * The truth is that merging correctly the initial with the updated data didn't go very well
     * from the programming scope. This was a result of the design I'd decided to follow while implementing the app.
     * Sure, in case the model served to the UI was `more` flat everything would be easier.
     *
     * By the time, I thought of this it was too late and I didn't want to take the risk changing to many source files
     * of the UI, since this it would be followed by many many changes and testing as well.
     * Enjoy!
     */
    private fun getMergedWithCacheEventData(headlines: List<Headline>, games: List<Game>): Pair<List<Headline>, List<Game>>{
        if(!::cachedData.isInitialized)
            return headlines to games

        val mergedHeadlines = (cachedData.headlines + headlines)
            .flatMap { it.betViews }
            .distinctBy { it.id }
            .toList()
            .run { listOf(Headline(this))}


        val gamesData = cachedData.games + games

        //PLEASE HAVE A BEER AND ENJOY!!!!!!!!!!!!!!!!!!!!!!!!!
        val competitionContextMap = gamesData.flatMap { it.betViews }.groupBy { it.competitionCaption }
            .map {
                it.key to it.value.flatMap { it.competitions }.distinctBy { it.id }.map { it.id }
            }.toMap()

        val mergedGames = gamesData.flatMap { it.betViews }
            .groupBy { it.competitionCaption }
            .map { it ->
                val betviews = it.value

                //events ids per competition id
                val eventIdsByCompetitionsMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
                //distinct competitions
                val competitionEventsByIdMap = betviews.flatMap { it.competitions }.distinctBy { it.id }.map { it.id to Pair<String, MutableSet<Event>>(it.caption, mutableSetOf()) }.toMap()

                val competitionsMap = betviews.map { it.competitions }.flatMap { it.map { it } }.groupBy { it.id }
                competitionsMap.forEach{
                    if(!eventIdsByCompetitionsMap.containsKey(it.key)){
                        eventIdsByCompetitionsMap[it.key] = mutableSetOf()
                    }
                    it.value.flatMap { it.events }.onEach {event ->
                        eventIdsByCompetitionsMap[it.key]!!.add(event.id)
                    }
                }

                val newBetViews = mutableListOf<BetView>()

                competitionsMap.map { it.value }
                    .flatMap { it }
                    .flatMap { it.events }
                    .distinctBy { it.id }
                    .map { event ->
                        eventIdsByCompetitionsMap.forEach { entry ->
                            if (entry.value.contains(event.id)) {
                                competitionEventsByIdMap[entry.key]!!.second.add(event)
                            }
                        }
                        competitionEventsByIdMap
                    }

                for (entry in competitionEventsByIdMap) {
                    var betView: BetView? = null
                    var competitionContextName = ""
                    for (entry1 in competitionContextMap) {
                        if (entry1.value.contains(entry.key)) {
                            competitionContextName = it.key
                            break
                        }
                    }
                    if(competitionContextName.isBlank()){
                        Timber.e("continue because it was found that competitionContextName was empty")
                        continue
                    }
                    if(betView == null){
                        val foundBetView = newBetViews.firstOrNull { it.competitionCaption == competitionContextName }
                        if(foundBetView != null){
                            betView = foundBetView
                        }else{
                            betView = BetView(competitionContextName, listOf())
                            newBetViews.add(betView)
                        }
                    }
                    betView.apply {
                        val mutableList = competitions.toMutableList()
                        mutableList.add(Competition(entry.key, entry.value.first, entry.value.second.toList()))
                        this.competitions = mutableList
                    }
                }
                newBetViews
            }.flatMap {
                it.toList()
            }

        return mergedHeadlines to listOf(Game(mergedGames))
    }

    internal companion object {
        fun create(gameBookApi: GameBookApi): BetsRepository {
            val datasource = BetsRemoteDataSource(gameBookApi)
            return BetsRepositoryImpl(datasource)
        }
    }
}
