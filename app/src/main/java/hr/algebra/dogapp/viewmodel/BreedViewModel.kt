package hr.algebra.dogapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.algebra.dogapp.network.BreedFetcher

class BreedViewModel(private val breedFetcher: BreedFetcher) : ViewModel() {
    private val _breedsLiveData = MutableLiveData<Map<String, List<String>>>()
    val breedsLiveData: LiveData<Map<String, List<String>>> get() = _breedsLiveData

    fun fetchDogBreeds() {
        breedFetcher.fetchDogBreeds { breeds ->
            _breedsLiveData.postValue(breeds)
        }
    }
}
