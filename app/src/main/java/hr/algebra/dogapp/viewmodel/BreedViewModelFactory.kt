package hr.algebra.dogapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.algebra.dogapp.network.BreedFetcher

class BreedViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedViewModel::class.java)) {
            val breedFetcher = BreedFetcher()
            return BreedViewModel(breedFetcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}