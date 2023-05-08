package gr.jvoyatz.gamebook.features.gamebook.adapter

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions.CompetitionEventsView
import gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions.CompetitionEventsViewResolver
import gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.headline.HeadlineBetView
import gr.jvoyatz.gamebook.features.gamebook.models.ui.GameBookUiModel
import gr.jvoyatz.gamebook.features.gamebook.models.ui.ID_COMPETITION
import gr.jvoyatz.gamebook.features.gamebook.models.ui.ID_HEADER
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineBetViewUiModel

class GameBookUiAdapter(
    private val handler: Handler,
) : ListAdapter<GameBookUiModel, RecyclerView.ViewHolder>(GameBookUiDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GameBookUiModel.Header -> ID_HEADER
            is GameBookUiModel.Competition -> ID_COMPETITION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ID_COMPETITION -> GameViewHolder(CompetitionEventsView(parent.context, null, handler))
            ID_HEADER -> HeaderViewHolder(HeadlineBetView(parent.context, null, handler))
            else -> throw IllegalStateException("unknown id")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is HeaderViewHolder && item is GameBookUiModel.Header) {
            holder.bind(item.model)
        } else if (holder is GameViewHolder && item is GameBookUiModel.Competition) {
            holder.bind(item)
        }
    }

    inner class HeaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(betViewUiModels: List<HeadlineBetViewUiModel>) {
            (view as HeadlineBetView).setBetViews(betViewUiModels)
        }
    }

    inner class GameViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(competition: GameBookUiModel.Competition) {
            val competitionTitle = competition.competition
            val competitions = competition.uiModels

            CompetitionEventsViewResolver(
                view as CompetitionEventsView,
                competitionTitle,
                competitions,
                competition.expanded
            ) {
                handleOnExpand(competition)
            }
        }

        private fun handleOnExpand(competition: GameBookUiModel.Competition) {

            val index = currentList.indexOf(competition)
            if (index >= 0) {
                val model = currentList[index]
                if (model.id == ID_COMPETITION) {
                    with((model as GameBookUiModel.Competition)) {
                        expanded = !expanded
                    }
                    notifyItemChanged(index)
                }
            }
        }
    }
}