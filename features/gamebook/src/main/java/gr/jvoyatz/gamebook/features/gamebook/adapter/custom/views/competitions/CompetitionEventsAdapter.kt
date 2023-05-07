package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.textview.MaterialTextView
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.EventUiModel
import gr.jvoyatz.gamebook.features.sportsbook.databinding.ItemGamesCompetitionEventBinding
import gr.jvoyatz.gamebook.features.sportsbook.databinding.ItemGamesCompetitionsCaptionBinding
import gr.jvoyatz.gamebook.libraries.shared.Constants
import gr.jvoyatz.gamebook.libraries.shared.DateUtilities


private const val HEADER = 11
private const val ITEM = 22

class CompetitionEventsAdapter(
    private val handler: Handler
) : androidx.recyclerview.widget.ListAdapter<CompetitionUiModel, ViewHolder>(
    CompetitionEventlDiffCallBack()
) {

    override fun getItemViewType(position: Int): Int {
        return with(getItem(position)) {
            if (this is CompetitionUiModel.CompetitionUiHeader)
                return HEADER
            return ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> {
                val binding = ItemGamesCompetitionsCaptionBinding.inflate(inflater, parent, false)
                CompetitionHeaderViewHolder(binding)
            }

            else -> {
                CompetitionEventViewHolder(
                    ItemGamesCompetitionEventBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).run {
            when (this) {
                is CompetitionUiModel.CompetitionUiHeader -> {
                    (holder as CompetitionHeaderViewHolder).bind(this.header)
                }

                is CompetitionUiModel.CompetitionUiEvent -> {
                    (holder as CompetitionEventViewHolder).bind(this.event)
                }

                else -> {}
            }
        }
    }

    inner class CompetitionHeaderViewHolder(private val binding: ItemGamesCompetitionsCaptionBinding) :
        ViewHolder(binding.root) {
        fun bind(competition: String?) {
            binding.root.text = competition ?: Constants.DOTS
        }
    }

    inner class CompetitionEventViewHolder(
        private val binding: ItemGamesCompetitionEventBinding
    ) : ViewHolder(binding.root) {

        private var runnable: TimerRunnable? = null
        fun bind(event: EventUiModel) {
            with(binding) {
                this.competitor1.text = event.competitor1
                this.competitor2.text = event.competitor2
                setTimeStamp(this.elapsedTime, event)
            }
        }

        private fun setTimeStamp(elapsedTime: MaterialTextView, competition: EventUiModel) {
            val timestampInMillis =
                DateUtilities.getTimeInMillisFromTimestampDate(competition.elapsed)
            timestampInMillis?.let {

                runnable?.let { handler.removeCallbacks(it) }
                runnable = TimerRunnable(timestampInMillis, handler) {
                    elapsedTime.text = it
                }

                handler.post(runnable!!)
            } ?: kotlin.run {
                elapsedTime.text = Constants.DASH_WITH_SPACE
            }
        }
    }

    internal inner class TimerRunnable(
        private val timestampInMillis: Long,
        private val handler: Handler,
        private val block: (String) -> Unit
    ) : Runnable {
        override fun run() {
            val diffFromNow = System.currentTimeMillis() - timestampInMillis
            DateUtilities.convertMillisToHHMMSS(diffFromNow).also {
                block(it)
            }
            handler.postDelayed(this, Constants.TIME_MILLIS)
        }
    }
}