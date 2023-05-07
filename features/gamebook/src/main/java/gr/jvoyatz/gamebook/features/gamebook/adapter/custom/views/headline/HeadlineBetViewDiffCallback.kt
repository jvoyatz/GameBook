package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.headline

import androidx.recyclerview.widget.DiffUtil
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineBetViewUiModel

class HeadlineBetViewDiffCallback: DiffUtil.ItemCallback<HeadlineBetViewUiModel>() {
    override fun areItemsTheSame(oldItem: HeadlineBetViewUiModel, newItem: HeadlineBetViewUiModel): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: HeadlineBetViewUiModel, newItem: HeadlineBetViewUiModel): Boolean {
        return oldItem == newItem
    }
}