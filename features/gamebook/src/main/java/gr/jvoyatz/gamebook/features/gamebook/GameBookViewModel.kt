package gr.jvoyatz.gamebook.features.gamebook

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.jvoyatz.gamebook.components.bets.domain.interactors.getEventDataUseCase
import gr.jvoyatz.gamebook.features.gamebook.GameBookContract.GameBookUiState
import gr.jvoyatz.gamebook.features.gamebook.GameBookContract.Partial
import gr.jvoyatz.gamebook.features.gamebook.models.mappers.GameBookUiModelMapper.toUiModels
import gr.jvoyatz.gamebook.libraries.mvvmi.BaseViewModel
import gr.jvoyatz.gamebook.libraries.shared.resultdata.onSuspendedError
import gr.jvoyatz.gamebook.libraries.shared.resultdata.onSuspendedSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameBookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getData: getEventDataUseCase
): BaseViewModel<GameBookContract.State, Partial, GameBookContract.Event, GameBookContract.Effect>(
    savedStateHandle, GameBookContract.State(GameBookUiState.Initialize)
) {
    private lateinit var eventsUpdateJob: Job
    override fun mapEvents(intent: GameBookContract.Event): Flow<Partial> {
        return when(intent) {
            GameBookContract.Event.Initialize -> { getInitialData() }
            GameBookContract.Event.Update -> { getUpdatedData() }
        }
    }

    override fun reducePartialToUiState(
        uiState: GameBookContract.State,
        partialState: Partial
    ): GameBookContract.State {

        return uiState.copy(
            state = when (partialState) {
                is Partial.Data -> {
                    GameBookUiState.Data(partialState.data.toUiModels()).apply {
                        isInit = partialState.isInit
                    }
                }
                is Partial.NoData -> GameBookUiState.NoData
                is Partial.Loading -> GameBookUiState.Loading
                is Partial.Error -> GameBookUiState.Error
            }
        )
    }
    private fun getInitialData() = flow {
        emit(Partial.Loading)
        getData(true)
            .onSuspendedSuccess {
                Timber.d("eventsDataContainer reeceived success! } ${Thread.currentThread()}")
                if (!it.isEmpty()) {
                    emit(Partial.Data(it).apply {
                        isInit = true
                    })
                } else {
                    emit(Partial.NoData)
                }
            }.onSuspendedError {
                it.printStackTrace()
                emit(Partial.Error)
            }
    }.flowOn(Dispatchers.Default)

    private fun getUpdatedData() = flow<Partial> {
        getData(false)
            .onSuspendedSuccess {
                Timber.d("success while updating! ${Thread.currentThread()}")
                if (!it.isEmpty()) {
                    emit(Partial.Data(it))
                } else {
                    emit(Partial.NoData)
                }
            }.onSuspendedError {
                it.printStackTrace()
                emit(Partial.Error)
            }
    }
    .flowOn(Dispatchers.Default)
}