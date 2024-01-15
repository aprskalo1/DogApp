package hr.algebra.dogapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.algebra.dogapp.network.DogFetcher

@Suppress("UNCHECKED_CAST")
class DogViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            val dogFetcher = DogFetcher(context)
            return DogViewModel(dogFetcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}