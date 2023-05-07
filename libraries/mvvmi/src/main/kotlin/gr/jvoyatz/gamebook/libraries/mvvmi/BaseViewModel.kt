package gr.jvoyatz.gamebook.libraries.mvvmi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Using this Base class we tell our View to draw something
 * related to the UiState field's value.
 *
 * This UiState is the result of the processing that takes place when a new
 * user intent is passed.
 *
 * In case of an error (eg a network error) then we show a UiSideEffect instance
 *
 * For each case of those described above, we use a different technology to achieve our purpose.
 * For the UiState we use StateFlow, something similar to Livedata but with a Initial Value.
 *
 * For the UiIntent we use MutableSharedFlow, which in case of an absence of a subscriber,
 * the value (that is) posted is dropped. Why handling an Intent when no subscribers exist?
 *
 * For the UiEffect, I use Channels because Channel's value is being posted immediately and once.
 * That means that since channels are a hot stream, when we change the devices orientation we won't show this
 * effect again.
 *
 * However as described in this article, this is an `anti-pattern` and it is preferable to use UiState to handle such cases and reset its state.
 * See [this](https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95)
 *
 * For now i will use Channels.
 */

private const val SAVED_UI_STATE_KEY = "SAVED_UI_STATE_KEY"
@Suppress("unused")
abstract class BaseViewModel<State: UiState, Reduce: PartialUiState, Event: UiEvent, Effect: UiEffect>(
    private val savedStateHandle: SavedStateHandle,
    initialState: State //initial value to be rendered
) : ViewModel() {

    private val eventFlow : MutableSharedFlow<Event> = MutableSharedFlow()

    val uiState = savedStateHandle.getStateFlow(SAVED_UI_STATE_KEY, initialState)

    private val _uiEffectChannel = Channel<Effect>(Channel.BUFFERED)
    val uiEffect = _uiEffectChannel.receiveAsFlow()

    init {
        subscribeUiEvents()
    }

    /**
     * When a new emissions takes place in the intentFlow,
     * the `flatMapMerge` function is called and transforms the emitted Event into
     * another flow.
     * This flow contains a InternalPartialState value.
     * After the scan method is called, we pass this flow's value (aka PartialUiState) and the current value of the uiState's flow
     * to a function that returns (or reduces) the PartialUiState into UiState.
     *
     * In the end we set this value to the uiStateFlow.
     *
     * Note: Keep in mind that we use saveStateHandle to survive app's process death events.
     */
    @OptIn(FlowPreview::class)
    private fun subscribeUiEvents(){
        viewModelScope.launch {
            eventFlow
                .flatMapMerge { mapEvents(it) }
                .scan(uiState.value, ::reducePartialToUiState)
                .catch { Timber.e(it) }
                .collect {
                    savedStateHandle[SAVED_UI_STATE_KEY] = it
                }

        }
    }

    /**
     * Accepts an argument, which is an instance of [Event], representing an action requested by the user.
     * This will trigger a new emission in the [eventFlow].
     */
    fun onNewEvent(event: Event) = viewModelScope.launch {
        Timber.tag("BASE_VIEWMODEL").i("RECEIVED EVENT $event")
        eventFlow.emit(event)
    }

    /**
     * Posts a new event when we want to show a dialog to the user
     */
    @Deprecated("use  postEffect(builder: () -> Effect)")
    private fun postEffect(event: Effect){
        viewModelScope.launch {
            _uiEffectChannel.send(event)
        }
    }
    protected fun postEffect(builder: () -> Effect){
        //postEffect(builder())
        builder().let { effect ->
            viewModelScope.launch { _uiEffectChannel.send(effect) }
        }
    }

    protected fun setState(reduce: State.() -> State) {
//        val newState = currentState.reduce()
//        uiState.
    }


    /**
     * Check what exactly is this user's given event, executes the corresponding action
     * `mapped` for this event and after having (this action's) result, it generates a Partial Ui State,
     * that will later  processed in order to update publish the final UiState that our screen observes
     */
    protected abstract fun mapEvents(intent: Event): Flow<Reduce>


    /**
     * It updates the uiState in order to contain the new data included in the (new) Partial Ui State
     */
    protected abstract fun reducePartialToUiState(
        uiState: State,
        partialState: Reduce
    ): State
}