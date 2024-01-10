package hr.algebra.dogapp.fragment

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import hr.algebra.dogapp.R
import hr.algebra.dogapp.adapter.ImageAdapter
import hr.algebra.dogapp.databinding.FragmentFavouritesBinding
import hr.algebra.dogapp.model.ImageItem
import java.io.File

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val dirPath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "/Favourites"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val imageList = getListOfImages()
        val imageAdapter = ImageAdapter(imageList)

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = imageAdapter
    }

    private fun getListOfImages(): List<ImageItem> {
        val imageDir = File(dirPath)
        val imageFiles = imageDir.listFiles { _, name ->
            name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png")
        }

        return imageFiles?.map { ImageItem(it.absolutePath) } ?: emptyList()
    }
}