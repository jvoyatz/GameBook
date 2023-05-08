package gr.jvoyatz.gamebook.features.gamebook

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.jvoyatz.gamebook.components.bets.domain.interactors.GetAuthTokenUseCase
import gr.jvoyatz.gamebook.components.bets.domain.interactors.GetEventDataUseCase
import gr.jvoyatz.gamebook.features.gamebook.GameBookContract.GameBookUiState
import gr.jvoyatz.gamebook.features.gamebook.GameBookContract.Partial
import gr.jvoyatz.gamebook.features.gamebook.models.mappers.GameBookUiModelMapper.toUiModels
import gr.jvoyatz.gamebook.libraries.mvvmi.BaseViewModel
import gr.jvoyatz.gamebook.libraries.shared.resultdata.onSuccess
import gr.jvoyatz.gamebook.libraries.shared.resultdata.onSuspendedError
import gr.jvoyatz.gamebook.libraries.shared.resultdata.onSuspendedSuccess
import gr.jvoyatz.gamebook.libraries.ui.R
import gr.jvoyatz.gamebook.libraries.utils.android.AppDispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameBookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getData: GetEventDataUseCase,
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val appDispatchers: AppDispatchers
): BaseViewModel<GameBookContract.State, Partial, GameBookContract.Event, GameBookContract.Effect>(
    savedStateHandle, GameBookContract.State(GameBookUiState.Authenticate)
) {

    override fun mapEvents(intent: GameBookContract.Event): Flow<Partial> {
        return when(intent) {
            GameBookContract.Event.Initialize -> { getInitialData() }
            GameBookContract.Event.Update -> { getUpdatedData() }
            GameBookContract.Event.Authenticate -> { getAuthToken() }
        }
    }

    override fun reducePartialToUiState(
        uiState: GameBookContract.State,
        partialState: Partial
    ): GameBookContract.State {

        return uiState.copy(
            state = when (partialState) {
                is Partial.Data -> {
                    Timber.d("Thread.current ${Thread.currentThread()}")
                    GameBookUiState.Data(partialState.data).apply {
                        isInit = partialState.isInit
                    }
                }
                is Partial.NoData -> GameBookUiState.NoData
                is Partial.Loading -> GameBookUiState.Loading
                is Partial.Error -> GameBookUiState.Error
                is Partial.Initialize -> GameBookUiState.Initialize
            }
        )
    }
    private fun getInitialData() = flow {
        emit(Partial.Loading)
        getData(true)
            .onSuspendedSuccess {
                if (!it.isEmpty()) {
                    emit(Partial.Data(it.toUiModels()).apply {
                        isInit = true
                    })
                } else {
                    emit(Partial.NoData)
                }
            }.onSuspendedError {
                it.printStackTrace()
                emit(Partial.Error)
            }
    }.flowOn(appDispatchers.default)

    private fun getUpdatedData() = flow<Partial> {
        getData(false)
            .onSuspendedSuccess {
                if (!it.isEmpty()) {
                    emit(Partial.Data(it.toUiModels()))
                } else {
                    emit(Partial.NoData)
                }
                postEffect {
                    GameBookContract.Effect.ShowToast(R.string.update_success)
                }
            }.onSuspendedError {
                it.printStackTrace()
                emit(Partial.Error)
                postEffect {
                    GameBookContract.Effect.ShowToast(R.string.update_error)
                }
            }
    }
    .flowOn(appDispatchers.default)

    private fun getAuthToken()= flow<Partial> {
        emit(Partial.Loading)
        getAuthTokenUseCase()
            .onSuccess {

                postEffect {
                    GameBookContract.Effect.ShowToast(R.string.token_success)
                }
                delay(5000)
                emit(Partial.Initialize)
            }
            .onSuspendedError {
                postEffect {
                    GameBookContract.Effect.ShowToast(R.string.token_error)
                }
                emit(Partial.Error)
            }
    }
}