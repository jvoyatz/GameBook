package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel
import gr.jvoyatz.gamebook.features.sportsbook.databinding.ItemGamesCompetitionsBinding

class CompetitionEventsView(
    context: Context,
    attributeSet: AttributeSet?,
    handler: Handler
) : ConstraintLayout(context, attributeSet), CompetitionEventViewInterface{

    private val binding: ItemGamesCompetitionsBinding

    private val competitionsAdapter = CompetitionEventsAdapter(handler)
    init {
        val inflater = LayoutInflater.from(context)
        binding = ItemGamesCompetitionsBinding.inflate(inflater, this, true)

        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams = lp

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        with(binding.list){
            adapter = competitionsAdapter
        }
    }

    override fun setTitle(title: String) {
        binding.title.text = title
    }

    override fun setIcon(resId: Int) {
        binding.icon.setImageResource(resId)
    }

    override fun setEvents(events: List<CompetitionUiModel>) {
        with(binding){
            noData.isVisible = false
            list.isVisible = true
            competitionsAdapter.submitList(events)
        }
    }

    override fun showNoEvents() = with(binding){
        list.isVisible = false
        noData.isVisible = true
    }

    override fun setOnExpandClicked(block: () -> Unit) {
        binding.header.setOnClickListener {
            block()
        }
    }

    override fun collapse() = with(binding){
        list.isVisible = false
        arrow.rotation = 180f
    }
    override fun expand() = with(binding){
        list.isVisible = true
        arrow.rotation = 0f
    }
}