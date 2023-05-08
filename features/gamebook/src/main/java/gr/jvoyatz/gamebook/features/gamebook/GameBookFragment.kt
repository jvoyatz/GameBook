package gr.jvoyatz.gamebook.features.gamebook

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import gr.jvoyatz.gamebook.features.gamebook.GameBookContract.GameBookUiState
import gr.jvoyatz.gamebook.features.gamebook.adapter.GameBookUiAdapter
import gr.jvoyatz.gamebook.features.gamebook.models.ui.GameBookUiModel
import gr.jvoyatz.gamebook.features.sportsbook.databinding.FragmentGamesBookBinding
import gr.jvoyatz.gamebook.libraries.ui.utils.hide
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GameBookFragment : Fragment() {

    private var updatingJob: Job? = null

    @Inject
    lateinit var handler: Handler

    private lateinit var binding: FragmentGamesBookBinding
    private val viewModel by viewModels<GameBookViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentGamesBookBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        setupLoaderView()
        setupObservers()
    }

    private fun setupRecyclerViews(){
        val adapter = GameBookUiAdapter(handler)
        binding.dataList.apply {
            this.adapter = adapter
        }
    }

    private fun setupLoaderView() {
        with(binding.loaderView){
            this.setRetryListener {
                viewModel.onNewEvent(GameBookContract.Event.Initialize)
            }
        }
    }
    private fun setupObservers(){
        viewModel.uiState
            .map { it.state }
            .onEach {
                handleScreenState(it)
            }
            .launchIn(lifecycleScope)

        observeUiEffect()
    }

    private fun observeUiEffect(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEffect.collect {
                    handleUiEffect(it)
                }
            }
        }
    }

    private fun handleUiEffect(effect: GameBookContract.Effect){
        when(effect){
            is GameBookContract.Effect.ShowToast -> {
                val safeContext = context ?: return
                Toast.makeText(safeContext, effect.resourceId, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleScreenState(state: GameBookUiState){
        when(state){
            GameBookUiState.Authenticate -> viewModel.onNewEvent(GameBookContract.Event.Authenticate)
            GameBookUiState.Initialize -> viewModel.onNewEvent(GameBookContract.Event.Initialize)
            GameBookUiState.Update -> viewModel.onNewEvent(GameBookContract.Event.Update)
            GameBookUiState.Loading -> showLoadingState()
            GameBookUiState.NoData -> showNoDataState()
            is GameBookUiState.Data -> showDataState(state.uiModels, state.isInit)
            GameBookUiState.Error -> showErrorState()
        }
    }

    //ui state methods
    private fun showLoadingState(){
        with(binding){
            this.dataList.isVisible = false
            this.loaderView.showLoading()
        }
    }

    private fun showDataState(uiModels: List<GameBookUiModel>, isInit: Boolean) {
        with(binding){
            val adapter = binding.dataList.adapter as GameBookUiAdapter
            this.loaderView.hide()

            adapter.submitList(uiModels)
            dataList.isVisible = true
        }
        if(isInit){
            updatingJob = lifecycleScope.launch {
                delay(10_000)
                viewModel.onNewEvent(GameBookContract.Event.Update)
            }
        }
    }

    private fun showNoDataState(){
        with(binding){
            this.loaderView.showNoData()
            this.dataList.isVisible = false
        }
    }

    private fun showErrorState(){
        with(binding){
            loaderView.showError()
            this.dataList.isVisible = false
        }
    }

    private fun cleanup(){
        updatingJob?.cancel()
        handler.removeCallbacksAndMessages(null)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        cleanup()
    }
}