package hr.algebra.dogapp.network.api

import hr.algebra.dogapp.model.BreedItem
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://dog.ceo/api/"
const val BREEDS_ENDPOINT = "breeds/list/all"
const val IMAGES_ENDPOINT = "breed/{breed}/images"

interface DogApi {
    @GET(BREEDS_ENDPOINT)
    fun fetchDogBreeds(): Call<BreedItem>

    @GET(IMAGES_ENDPOINT)
    fun fetchDogImages(): Call<BreedItem>
}