package hr.algebra.dogapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.algebra.dogapp.network.DogFetcher

@Suppress("UNCHECKED_CAST")
class DogViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            val dogFetcher = DogFetcher()
            return DogViewModel(dogFetcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}