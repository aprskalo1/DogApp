package hr.algebra.dogapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import hr.algebra.dogapp.databinding.ActivitySplashScreenBinding
import hr.algebra.dogapp.framework.callDelayed
import hr.algebra.dogapp.framework.isOnline
import hr.algebra.dogapp.framework.startActivity

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
            callDelayed(
                SPLASH_SCREEN_DURATION
            ) {
                startActivity<HostActivity>()
            }
        } else {
            binding.tvSplashScreen.text = getString(R.string.no_internet_connection)
            callDelayed(
                SPLASH_SCREEN_DURATION
            ) {
                finish()
            }
        }
    }
}