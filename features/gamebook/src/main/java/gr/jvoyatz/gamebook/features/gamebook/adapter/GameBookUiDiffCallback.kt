package gr.jvoyatz.gamebook.features.gamebook.adapter

import androidx.recyclerview.widget.DiffUtil
import gr.jvoyatz.gamebook.features.gamebook.models.ui.GameBookUiModel

class GameBookUiDiffCallback : DiffUtil.ItemCallback<GameBookUiModel>() {
    override fun areItemsTheSame(oldItem: GameBookUiModel, newItem: GameBookUiModel): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: GameBookUiModel, newItem: GameBookUiModel): Boolean {
        return oldItem == newItem
    }
}