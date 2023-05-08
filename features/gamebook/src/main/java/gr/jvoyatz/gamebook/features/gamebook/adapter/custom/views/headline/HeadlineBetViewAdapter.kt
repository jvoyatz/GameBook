package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.headline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.headline.HeadlineBetViewItemsAdapter.HeadlineBetItemViewHolder
import gr.jvoyatz.gamebook.features.gamebook.databinding.ItemGamesHeadlineViewBinding
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineBetViewUiModel
import gr.jvoyatz.gamebook.libraries.shared.Constants

class HeadlineBetViewItemsAdapter :
    ListAdapter<HeadlineBetViewUiModel, HeadlineBetItemViewHolder>(HeadlineBetViewDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineBetItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HeadlineBetItemViewHolder(
            ItemGamesHeadlineViewBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeadlineBetItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HeadlineBetItemViewHolder(
        private val binding: ItemGamesHeadlineViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HeadlineBetViewUiModel) {
            with(binding) {
                competitor1.text = data.competitor1 ?: Constants.DASH_WITH_SPACE
                competitor2.text = data.competitor2 ?: Constants.DASH_WITH_SPACE
                startTime.text = data.startTime ?: Constants.DASH_WITH_SPACE
            }
        }
    }
}