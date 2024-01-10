package hr.algebra.dogapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.algebra.dogapp.R
import hr.algebra.dogapp.model.ImageItem

class ImageAdapter(private val ImageItems: List<ImageItem>) :
    RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ImageItems.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageItem = ImageItems[position]
        Glide.with(holder.itemView)
            .load(imageItem.imagePath)
            .into(holder.imageView)
    }
}