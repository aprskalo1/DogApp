package hr.algebra.dogapp.network

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.dogapp.DOG_PROVIDER_CONTENT_URI
import hr.algebra.dogapp.framework.toDogEntity
import hr.algebra.dogapp.model.DogItem
import hr.algebra.dogapp.network.api.BASE_URL
import hr.algebra.dogapp.network.api.DogApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import java.util.UUID


class DogFetcher(private val context: Context) {
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
                    storeItem(response.body()!!)
                } else {
                    Log.e("API", response.message())
                }
            }

            override fun onFailure(call: Call<DogItem>, t: Throwable) {
                Log.e("API", t.message, t)
            }
        })
    }

    private fun storeItem(DogItem: DogItem) {
        val scope = CoroutineScope(Dispatchers.IO)
        val id =  UUID.randomUUID().toString()

        val dogEntity = DogItem.toDogEntity(id)

        val values = ContentValues().apply {
            put("_id", id)
            put("message", dogEntity.message)
            put("status", dogEntity.status)
        }
        scope.launch {
            context.contentResolver.insert(
                DOG_PROVIDER_CONTENT_URI,
                values
            )
        }
    }
}