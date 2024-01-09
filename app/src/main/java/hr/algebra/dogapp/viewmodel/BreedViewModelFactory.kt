package hr.algebra.dogapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.algebra.dogapp.network.BreedFetcher

@Suppress("UNCHECKED_CAST")
class BreedViewModelFactory(private val  context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedViewModel::class.java)) {
            return BreedViewModel(BreedFetcher(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}