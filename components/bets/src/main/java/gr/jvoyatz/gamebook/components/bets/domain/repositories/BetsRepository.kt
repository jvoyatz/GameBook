package gr.jvoyatz.gamebook.components.bets.domain.repositories

import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.libraries.shared.resultdata.ResultData


/**
 * Contract which defines the methods used to get the current available bets data
 */
interface BetsRepository {

    /**
     * fetches the auth token
     */
    suspend fun getToken(): ResultData<Unit>
    /**
     * Get the initial bets
     */
    suspend fun getData(): ResultData<EventsDataContainer>

    /**
     * Get the updated bets
     */
    suspend fun getUpdatedData(): ResultData<EventsDataContainer>
}
