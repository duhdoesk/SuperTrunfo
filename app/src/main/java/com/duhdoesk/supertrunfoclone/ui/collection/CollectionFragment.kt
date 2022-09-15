package com.duhdoesk.supertrunfoclone.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.databinding.FragmentCollectionBinding
import com.duhdoesk.supertrunfoclone.model.Deck
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    private val collectionAdapter = CollectionAdapter()

    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.collectionRecyclerView

        collectionAdapter.onCLickListener = { deck: Deck ->
            findNavController().navigate(
                CollectionFragmentDirections.actionDestinationCollectionToDestinationInGame(deck.id)
            )
        }

        viewModel.decks.observe(viewLifecycleOwner) {
            collectionAdapter.setDecks(it)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = collectionAdapter
        }
    }
}