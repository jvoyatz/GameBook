package gr.jvoyatz.gamebook.features.gamebook.adapter.custom.views.headline

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayoutMediator
import gr.jvoyatz.gamebook.features.gamebook.models.ui.headlines.HeadlineBetViewUiModel
import gr.jvoyatz.gamebook.features.sportsbook.databinding.ItemGamesHeadlineBinding
import gr.jvoyatz.gamebook.libraries.ui.utils.startAutoScrollAndGetRunnable

class HeadlineBetView(
    context: Context,
    attributeSet: AttributeSet?,
    private val handler: Handler
) : ConstraintLayout(context, attributeSet), HeadlineBetViewInterface {

    private var autoScrollRunnable: Runnable? = null
    private val binding: ItemGamesHeadlineBinding
    private val betViewsAdapter: HeadlineBetViewItemsAdapter = HeadlineBetViewItemsAdapter()

    init {
        val inflater = LayoutInflater.from(context)
        binding = ItemGamesHeadlineBinding.inflate(inflater, this, true)

        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams = lp

        setupViewPager()
    }

    private fun setupViewPager() {
        with(binding.headlinesPager){
            adapter = betViewsAdapter.apply {
                setHasStableIds(true)
            }
            TabLayoutMediator(binding.headlinesTabs, this) { _, _ -> }.attach()
        }
    }
    override fun setBetViews(betViewUiModels: List<HeadlineBetViewUiModel>) {
        val list = betViewUiModels.toMutableList()
//        list.add(betViewUiModels[0].copy(id = 14, competitor2 = "ΑΕΚ"))
//        list.add(betViewUiModels[0].copy(id = 15, competitor2 = "ΑΕΚ2"))
//        list.add(betViewUiModels[0].copy(id = 16, competitor2 = "ΑΕΚ3"))
        betViewsAdapter.submitList(list)

        autoScrollRunnable?.let {
            handler.removeCallbacks(it)
        }
        autoScrollRunnable = binding.headlinesPager.startAutoScrollAndGetRunnable(handler)
    }
}