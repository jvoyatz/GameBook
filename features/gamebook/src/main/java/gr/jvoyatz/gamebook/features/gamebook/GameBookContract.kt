package gr.jvoyatz.gamebook.features.gamebook

import android.os.Parcelable
import androidx.annotation.StringRes
import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.features.gamebook.models.ui.GameBookUiModel
import gr.jvoyatz.gamebook.libraries.mvvmi.PartialUiState
import gr.jvoyatz.gamebook.libraries.mvvmi.UiEffect
import gr.jvoyatz.gamebook.libraries.mvvmi.UiEvent
import gr.jvoyatz.gamebook.libraries.mvvmi.UiState
import kotlinx.parcelize.Parcelize

object GameBookContract {

    /**
     * Used to indicate whether a toast (aka an effect) should be displayed
     */
    sealed interface Effect: UiEffect{
        data class ShowToast(@StringRes val resourceId: Int) : Effect
    }

    /**
     * Used to indicate an action that our viewmodel should execute
     */
    sealed interface Event: UiEvent {
        object Initialize: Event
        object Update: Event
    }

    /**
     * Contains the data received after a certain [Event] triggered by the user.
     * Those data will be later passed to the uiState flow.
     */
    sealed interface Partial: PartialUiState {
        data class Data(val data: EventsDataContainer): Partial{
            var isInit: Boolean = false
        }
        object NoData: Partial
        object Error: Partial
        object Loading: Partial
    }

    @Parcelize
    data class State(
        val state: GameBookUiState
    ) : UiState

    /**
     * State is the type exposed by viewmodel and observed by
     * the View, which holds the a sealed class type representing
     * what the UI must render
     */
    @Parcelize
    sealed class GameBookUiState: Parcelable {
        object Initialize: GameBookUiState()
        object Update: GameBookUiState()
        object Loading : GameBookUiState()
        object Error : GameBookUiState()
        object NoData : GameBookUiState()
        data class Data(val uiModels: List<GameBookUiModel>) : GameBookUiState(){
            var isInit: Boolean = false
        }
    }
}