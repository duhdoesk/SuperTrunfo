package com.duhdoesk.supertrunfoclone.presentation.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.model.Deck


class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var listOfDecks: List<Deck> = emptyList()

    fun setDecks(decks: List<Deck>) {
        listOfDecks = decks
    }

    var onCLickListener: ((Deck) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: View = view.findViewById(R.id.root)
        private val tvCard: TextView = view.findViewById(R.id.cardview_text)
        private val ivCard: ImageView = view.findViewById(R.id.cardview_card)
        val bundle: Bundle = bundleOf("col" to adapterPosition)

        init {
            view.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_destination_collection_to_destination_inGame,
                    bundle
                )
            )
        }

        fun bind(deck: Deck) {
            tvCard.text = deck.name
            ivCard.load(deck.img)
            root.setOnClickListener {
                onCLickListener?.invoke(deck)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cards_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val deck = listOfDecks[position]
        viewHolder.bind(deck)
    }

    override fun getItemCount() = listOfDecks.size
}
