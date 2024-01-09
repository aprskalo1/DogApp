package hr.algebra.dogapp.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import hr.algebra.dogapp.viewmodel.BreedViewModel

class BreedWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        BreedFetcher(context).checkDogBreeds()
        return Result.success()
    }
}