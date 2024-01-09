package hr.algebra.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import hr.algebra.dogapp.databinding.ActivitySplashScreenBinding
import hr.algebra.dogapp.framework.callDelayed
import hr.algebra.dogapp.framework.isOnline
import hr.algebra.dogapp.framework.sendBroadcast
import hr.algebra.dogapp.framework.startActivity
import hr.algebra.dogapp.framework.userExists
import hr.algebra.dogapp.network.BreedWorker

private const val SPLASH_SCREEN_DURATION = 3000L

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        redirectToMainActivity()
    }

    private fun redirectToMainActivity() {
        if (isOnline()) {
            callDelayed(SPLASH_SCREEN_DURATION) {
                if (userExists()) {
                    WorkManager.getInstance(this).apply {
                        enqueueUniqueWork(
                            "dog-fetcher",
                            ExistingWorkPolicy.KEEP,
                            OneTimeWorkRequest.Companion.from(BreedWorker::class.java)
                        )
                    }
                } else {
                    startActivity<SignInActivity>()
                }
            }
        } else {
            binding.tvSplashScreen.text = getString(R.string.no_internet_connection)
            callDelayed(SPLASH_SCREEN_DURATION) {
                finish()
            }
        }
    }
}