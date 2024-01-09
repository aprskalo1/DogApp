package hr.algebra.dogapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.dogapp.framework.startActivity

class DogReceiver : BroadcastReceiver() {
    val apiStatusActive: Boolean
        get() {
            return Intent().getBooleanExtra("apiStatusActive", false)
        }

    override fun onReceive(context: Context, intent: Intent) {
        if (apiStatusActive) {
            if (context is SplashScreenActivity) context.finish()
        } else {
            context.startActivity<HostActivity>()
        }
    }
}