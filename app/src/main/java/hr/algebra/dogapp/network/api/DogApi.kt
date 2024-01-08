package hr.algebra.dogapp.network.api

import hr.algebra.dogapp.model.BreedItem
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://dog.ceo/api/"
const val BREEDS_ENDPOINT = "breeds/list/all"

interface DogApi {
    @GET(BREEDS_ENDPOINT)
    fun fetchDogBreeds(): Call<BreedItem>
}