package gr.jvoyatz.gamebook.features.gamebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.jvoyatz.gamebook.features.sportsbook.databinding.FragmentGamesBookBinding

@AndroidEntryPoint
class GameBookFragment : Fragment() {

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
}