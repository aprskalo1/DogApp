package hr.algebra.dogapp.network

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class BreedWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        BreedFetcher(context).fetchDogBreeds()
        return Result.success()
    }
}