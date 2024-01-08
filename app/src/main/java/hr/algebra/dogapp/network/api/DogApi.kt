package hr.algebra.dogapp.network.api

import hr.algebra.dogapp.model.BreedItem
import hr.algebra.dogapp.model.DogItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://dog.ceo/api/"
const val BREEDS_ENDPOINT = "breeds/list/all"
const val IMAGES_ENDPOINT = "breed/{breed}/images/random"

interface DogApi {
    @GET(BREEDS_ENDPOINT)
    fun fetchDogBreeds(): Call<BreedItem>

    @GET(IMAGES_ENDPOINT)
    fun fetchRandomDogImage(@Path("breed") breed: String): Call<DogItem>
}