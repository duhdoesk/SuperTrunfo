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
import com.duhdoesk.supertrunfoclone.datasource.Datasource
import com.duhdoesk.supertrunfoclone.ui.inGame.inGameHelper.Baralho


class CollectionAdapter(context: Context) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var listOfDecks: List<Baralho> = Datasource.getListOfDecks(context)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.cardview_text)
        var imageView: ImageView = view.findViewById(R.id.cardview_card)
        var bundle: Bundle = bundleOf("col" to adapterPosition)

        init {
            // Define click listener for the ViewHolder's View.

            view.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_destination_collection_to_destination_inGame,
                    bundle
                )
            )
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cards_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = listOfDecks[position].nome
        viewHolder.imageView.load(listOfDecks[position].cover)
        //viewHolder.imageView.setImageResource(listOfDecks[position].cover)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listOfDecks.size

}
