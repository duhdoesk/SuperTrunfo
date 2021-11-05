package com.duhdoesk.supertrunfoclone.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duhdoesk.supertrunfoclone.R


class CollectionAdapter() :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var title = arrayOf("Apresentadores Malucos", "Carros Tunados", "Mães Furiosas")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView
        var imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.cardview_text)
            imageView = view.findViewById(R.id.cardview_card)
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
        //viewHolder.imageView.setImageResource(R.drawable.ic_launcher_foreground)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = title.size

}
