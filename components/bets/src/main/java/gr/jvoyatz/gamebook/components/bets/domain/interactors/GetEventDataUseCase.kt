package gr.jvoyatz.gamebook.components.bets.domain.interactors

import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.libraries.shared.resultdata.ResultData


/**
 * Calls the methods which return the needed data from [BetsRepository]
 */
fun interface getEventDataUseCase: suspend (Boolean) -> ResultData<EventsDataContainer>
