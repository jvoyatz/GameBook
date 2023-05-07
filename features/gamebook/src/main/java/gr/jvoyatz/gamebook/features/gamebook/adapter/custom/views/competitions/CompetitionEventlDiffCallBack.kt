package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.competitions

import androidx.recyclerview.widget.DiffUtil
import gr.jvoyatz.gamebook.features.gamebook.models.ui.games.CompetitionUiModel


internal class CompetitionEventlDiffCallBack : DiffUtil.ItemCallback<CompetitionUiModel>() {
    override fun areItemsTheSame(
        oldItem: CompetitionUiModel,
        newItem: CompetitionUiModel
    ): Boolean {
        return oldItem.uiId == newItem.uiId
    }

    override fun areContentsTheSame(
        oldItem: CompetitionUiModel,
        newItem: CompetitionUiModel
    ): Boolean {
        return oldItem == newItem

    }
}