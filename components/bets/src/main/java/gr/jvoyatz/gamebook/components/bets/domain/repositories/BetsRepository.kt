package gr.jvoyatz.gamebook.components.bets.domain.repositories


/**
 * Contract which defines the methods used to get the current available bets data
 */
interface BetsRepository {

    /**
     * Get the initial bets
     */
    fun getBets()

    /**
     * Get the updated bets
     */
    fun getUpdatedBets()
}
