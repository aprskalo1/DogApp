package hr.algebra.dogapp.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.dogapp.R

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val imageView: ImageView = itemView.findViewById(R.id.ivFavouriteDog)
}