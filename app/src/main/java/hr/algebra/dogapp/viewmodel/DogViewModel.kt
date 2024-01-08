package hr.algebra.dogapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.algebra.dogapp.network.DogFetcher

class DogViewModel(private val dogFetcher: DogFetcher) : ViewModel() {
    private val _dogLiveData = MutableLiveData<String>()
    val dogLiveData: LiveData<String> get() = _dogLiveData

    fun fetchRandomDogImage(breed: String) {
        dogFetcher.fetchRandomDogImage(breed) { dog ->
            _dogLiveData.postValue(dog)
        }
    }
}