package com.duhdoesk.supertrunfoclone.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.duhdoesk.supertrunfoclone.R


class CollectionAdapter() :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var title = arrayOf(
        "Carros Irados",
        "Caçadores Ferozes",
        "Barcos Envenenados",
        "Cartoon Network",
        "Super Heróis DC",
        "Atletas Históricos",
        "Fórmula Truck"
    )

    private var art = arrayOf(
        R.drawable.carros_irados_art,
        R.drawable.cacadores_ferozes_art,
        R.drawable.barcos_envenenados_art,
        R.drawable.cartoon_network_art,
        R.drawable.super_herois_dc,
        R.drawable.atletas_historicos_art,
        R.drawable.formula_truck_art
    )

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
        viewHolder.textView.text = title[position]
        viewHolder.imageView.setImageResource(art[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = title.size

}
