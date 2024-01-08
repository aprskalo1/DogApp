package hr.algebra.dogapp.network

import android.content.Context
import android.util.Log
import hr.algebra.dogapp.DogReceiver
import hr.algebra.dogapp.framework.sendBroadcast
import hr.algebra.dogapp.model.BreedItem
import hr.algebra.dogapp.network.api.BASE_URL
import hr.algebra.dogapp.network.api.DogApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreedFetcher(private val context: Context) {
    private val dogApi: DogApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dogApi = retrofit.create(DogApi::class.java)
    }

    fun fetchDogBreeds() {
        dogApi.fetchDogBreeds().enqueue(object : Callback<BreedItem> {
            override fun onResponse(
                call: Call<BreedItem>,
                response: retrofit2.Response<BreedItem>
            ) {
                if (response.isSuccessful) {
                    //start the host activity
                } else {
                    Log.e("API", response.message())
                }
            }

            override fun onFailure(call: Call<BreedItem>, t: Throwable) {
                Log.e("API", t.message, t)
            }
        })
    }
}