package com.duhdoesk.supertrunfoclone.ui.collection

import android.content.Context
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
import com.duhdoesk.supertrunfoclone.datasource.DeckLocalDataSource
import com.duhdoesk.supertrunfoclone.model.Deck
import javax.inject.Inject


class CollectionAdapter @Inject constructor (private val context: Context, private val deckLocalDataSource: DeckLocalDataSource) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var listOfDecks: List<Deck> = deckLocalDataSource.loadDecks()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.cardview_text)
        var imageView: ImageView = view.findViewById(R.id.cardview_card)
        var bundle: Bundle = bundleOf("col" to adapterPosition)

        init {
            view.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_destination_collection_to_destination_inGame,
                    bundle
                )
            )
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cards_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = listOfDecks[position].name
        viewHolder.imageView.load(listOfDecks[position].img)
    }

    override fun getItemCount() = listOfDecks.size

}
