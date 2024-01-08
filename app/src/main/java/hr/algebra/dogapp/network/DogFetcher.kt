package hr.algebra.dogapp.network

import android.util.Log
import hr.algebra.dogapp.model.DogItem
import hr.algebra.dogapp.network.api.BASE_URL
import hr.algebra.dogapp.network.api.DogApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback


class DogFetcher {
    private val dogApi: DogApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dogApi = retrofit.create(DogApi::class.java)
    }

    fun fetchRandomDogImage(breed: String, callback: (String) -> Unit) {
        dogApi.fetchRandomDogImage(breed).enqueue(object : Callback<DogItem> {
            override fun onResponse(
                call: Call<DogItem>,
                response: retrofit2.Response<DogItem>
            ) {
                if (response.isSuccessful) {
                    callback(response.body()?.message ?: "")
                } else {
                    Log.e("API", response.message())
                }
            }

            override fun onFailure(call: Call<DogItem>, t: Throwable) {
                Log.e("API", t.message, t)
            }
        })
    }
}